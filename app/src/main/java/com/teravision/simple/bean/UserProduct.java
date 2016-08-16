package com.teravision.simple.bean;

import com.google.gson.Gson;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Sara Villarreal on 8/3/2016.
 */
public class UserProduct implements Serializable {
    private Long entityId;
    private Long productInventoryId;
    private Long userId;
    private Timestamp createdDate;

    public UserProduct() {
    }

    public UserProduct(Long entityId, Long productInventoryId, Long userId, Timestamp createdDate) {
        this.entityId = entityId;
        this.productInventoryId = productInventoryId;
        this.userId = userId;
        this.createdDate = createdDate;
    }

    public UserProduct EntityfromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, UserProduct.class);
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Long getProductInventoryId() {
        return productInventoryId;
    }

    public void setProductInventoryId(Long productInventoryId) {
        this.productInventoryId = productInventoryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
}
