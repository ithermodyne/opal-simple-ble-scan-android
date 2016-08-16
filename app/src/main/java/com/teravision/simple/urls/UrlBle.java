package com.teravision.simple.urls;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.teravision.simple.util.JsonObjectRequestUtil;
import com.teravision.simple.util.UtilSingleton;

import org.json.JSONObject;

/**
 * Created by Sara Villarreal on 7/27/2016.
 */
public class UrlBle {
    public final static String TAG = "urlLogin-tag";
    public final static String DEVELOPMENT_ACTIVIY_BLE = "userActivity/createActivity/";
    public static JsonObjectRequest jsonObjectRequest = null;


    public static void saveDataBle(Context context, String bleData, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {

        String bleUrl = UrlGlobal.createUrl(DEVELOPMENT_ACTIVIY_BLE);
        jsonObjectRequest = new JsonObjectRequestUtil(Request.Method.POST, bleUrl, bleData, listener, errorListener);
        jsonObjectRequest.setTag(TAG);
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(20000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        UtilSingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);

    }
}
