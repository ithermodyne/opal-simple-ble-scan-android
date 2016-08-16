package com.teravision.simple.bean;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by Sara Villarreal on 8/4/2016.
 */
public class ProductInventory implements Serializable {
    private Long entityId;
    private Long brandProductId;
    private Long itdSwatchId;

    public ProductInventory() {
    }

    public ProductInventory(Long entityId, Long brandProductId, Long itdSwatchId) {
        this.entityId = entityId;
        this.brandProductId = brandProductId;
        this.itdSwatchId = itdSwatchId;
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

    public Long getBrandProductId() {
        return brandProductId;
    }

    public void setBrandProductId(Long brandProductId) {
        this.brandProductId = brandProductId;
    }

    public Long getItdSwatchId() {
        return itdSwatchId;
    }

    public void setItdSwatchId(Long itdSwatchId) {
        this.itdSwatchId = itdSwatchId;
    }
}
