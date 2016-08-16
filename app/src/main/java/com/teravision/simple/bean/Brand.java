package com.teravision.simple.bean;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by Sara Villarreal on 8/3/2016.
 */
public class Brand implements Serializable {
    private Long entityId;
    private Long imageId;
    private String name;
    private String description;

    public Brand() {
    }

    public Brand(Long entityId, Long imageId, String name, String description) {
        this.entityId = entityId;
        this.imageId = imageId;
        this.name = name;
        this.description = description;
    }

    public Brand EntityfromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, Brand.class);
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
