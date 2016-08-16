package com.teravision.simple.bean;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by Sara Villarreal on 8/3/2016.
 */
public class Product implements Serializable {

    private Long entityId;
    private Long brandId;
    private Long productCategoryId;
    private Long imageId;
    private String name;
    private String description;
    private String type;
    private String notes;

    public Product() {
    }

    public Product(Long entityId, Long brandId, Long productCategoryId, Long imageId, String name, String description, String type, String notes) {
        this.entityId = entityId;
        this.brandId = brandId;
        this.productCategoryId = productCategoryId;
        this.imageId = imageId;
        this.name = name;
        this.description = description;
        this.type = type;
        this.notes = notes;
    }

    public User EntityfromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, User.class);
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
