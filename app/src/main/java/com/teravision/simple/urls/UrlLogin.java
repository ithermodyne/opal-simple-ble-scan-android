package com.teravision.simple.urls;

import com.android.volley.toolbox.JsonObjectRequest;

/**
 * Created by Sara Villarreal on 7/27/2016.
 */
public class UrlLogin {
    public final static String DEVELOPMENT_LOGIN_USER = "/users/login/";
    public final static String USER_EMAIL = "email";
    public final static String USER_PASSWORD = "password";
    public final static String DEVICE_TOKEN = "deviceToken";
    public final static String DEVICE_PLATFORM = "devicePlatform";

    public static JsonObjectRequest jsonObjectRequest = null;
    public final static String TAG = "urlLogin-tag";


}
