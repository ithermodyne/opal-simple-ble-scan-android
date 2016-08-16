package com.teravision.simple.urls;

import com.teravision.simple.util.UtilSingleton;

/**
 * Created by Sara Villarreal on 7/27/2016.
 */
public class UrlGlobal {

    public static String createUrl(String url){
        StringBuilder builder = new StringBuilder();
        builder.append(UtilSingleton.getInstance().getConfValue("URL"));
        builder.append(url);
        return builder.toString();
    }
}
