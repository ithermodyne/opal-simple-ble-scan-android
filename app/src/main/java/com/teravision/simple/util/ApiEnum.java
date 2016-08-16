package com.teravision.simple.util;

/**
 * Created by Edgard Rendon on 08/08/2016.
 */
public enum ApiEnum {

    SUCCESS(200),
    INTERNAL_SERVER_ERROR(500),
    USER_PASSWORD_INVALID(701);

    public int value;

    ApiEnum(int _value){
        value = _value;
    }
}
