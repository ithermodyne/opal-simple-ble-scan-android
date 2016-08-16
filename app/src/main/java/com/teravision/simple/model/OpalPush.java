package com.teravision.simple.model;

import java.io.Serializable;

/**
 * Created by Javier Díaz on 01/08/2016.
 */
public class OpalPush implements Serializable {

    private String message;
    private Boolean comingFromBackground = Boolean.FALSE;

    public OpalPush() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getComingFromBackground() {
        return comingFromBackground;
    }

    public void setComingFromBackground(Boolean comingFromBackground) {
        this.comingFromBackground = comingFromBackground;
    }
}
