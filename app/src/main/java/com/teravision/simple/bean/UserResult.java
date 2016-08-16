package com.teravision.simple.bean;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by Sara Villarreal on 8/4/2016.
 */
public class UserResult implements Serializable {
    private Long entityId;
    private Long productInventoryId;
    private ProductInventory productInventory;
    private ItdSwatch itdSwatch;
    private Product product;
    private Brand brand;

    public UserResult() {
    }

    public UserResult(Long entityId, Long productInventoryId, ProductInventory productInventory, ItdSwatch itdSwatch, Product product, Brand brand) {
        this.entityId = entityId;
        this.productInventoryId = productInventoryId;
        this.productInventory = productInventory;
        this.itdSwatch = itdSwatch;
        this.product = product;
        this.brand = brand;
    }

    public UserResult EntityfromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, UserResult.class);
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

    public ProductInventory getProductInventory() {
        return productInventory;
    }

    public void setProductInventory(ProductInventory productInventory) {
        this.productInventory = productInventory;
    }

    public ItdSwatch getItdSwatch() {
        return itdSwatch;
    }

    public void setItdSwatch(ItdSwatch itdSwatch) {
        this.itdSwatch = itdSwatch;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
