package com.teravision.simple.util;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * Created by Sara Villarreal on 7/27/2016.
 */
public class JsonUtil {

    public static <T> T getEntityFromObject(JSONObject jsonObject, Type type, Context context){

        Log.i("JsonUtil",jsonObject.toString());
        return UtilSingleton.getInstance(context).getGSON().fromJson(jsonObject.toString(), type);
    }

    public static <T> T getEntityFromJSON(String jsonObject, Type type){
        Log.i("JsonUtil",jsonObject);
        return UtilSingleton.getInstance().getGSON().fromJson(jsonObject.toString(), type);
    }

}
