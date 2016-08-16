package com.teravision.simple.util;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Javier Diaz on 15/06/2016.
 */
public class UtilSingleton {

    private static UtilSingleton utilSingleton = new UtilSingleton();
    private Context mContext;
    public  String STRING_DATE_PATTERN = "yyyy-MM-dd hh:mm:ss";

    /**
     *
     */
    private static Gson GSON;

    /**
     *
     */
    private RequestQueue mRequestQueue;

    /**
     *
     */
    private static GsonBuilder GSON_BUILDER;

    private static UtilSingleton sUtilSingleton;

    private UtilSingleton(){}

    public static UtilSingleton getInstance(){
        return utilSingleton;
    }

    private UtilSingleton(Context context) {
        mContext = context;
        GSON_BUILDER = new GsonBuilder();
        GSON_BUILDER.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        });
        GSON_BUILDER.setDateFormat(STRING_DATE_PATTERN);
        GSON = GSON_BUILDER.create();
    }

    private static Properties confProperties;

/*
Método debe ser inicializado por un tercero antes de llamar a las propiedades, de lo contrario será NULL
 */

    public static void loadProperties(Context context){
        InputStream inputStream = null;
        confProperties = new Properties();

        try {
            inputStream = context.getAssets().open("config.properties");
            confProperties.load(inputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public String getConfValue(String key){
        String result = "";
        try {
            result = confProperties.getProperty(key);
            if (result != null){

            }
            else {
                result =  confProperties.getProperty(key+"_"+confProperties.getProperty("ENVIROMENT"))+"/"+confProperties.getProperty("CONTEXT")+"/";

            }
        }catch (Exception e){

        }
        return result;
        //return "El key "+key+" no existe o no fue inicializado el método.";
    }


    public Gson getGSON() {
        if (GSON == null) {
            GSON = GSON_BUILDER.create();
        }
        return GSON;
    }
    /**
     * @param context
     * @return
     */
    public static synchronized UtilSingleton getInstance(Context context) {
        if (sUtilSingleton == null) {
            sUtilSingleton = new UtilSingleton(context);
        }
        return sUtilSingleton;
    }

    /**
     * @param req
     * @param <T>
     */
    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
    /**
     * @return
     */
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }
}
