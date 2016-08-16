package com.teravision.simple.model;

import java.io.Serializable;

/**
 * Created by Sara Villarreal on 7/19/2016.
 */
public class Brand implements Serializable {

    private String name;
    private String id;
    private String code;

    public Brand (String name, String id, String code){
        this.name = name;
        this.id = id;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
