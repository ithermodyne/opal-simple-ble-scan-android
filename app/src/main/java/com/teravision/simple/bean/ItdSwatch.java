package com.teravision.simple.bean;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by Sara Villarreal on 8/4/2016.
 */
public class ItdSwatch implements Serializable {
    private Long entityId;
    private String serialNumber;
    private String uuid;
    private String barcode;
    private String version;
    private Double width;
    private Double height;
    private Double length;

    public ItdSwatch() {
    }

    public ItdSwatch(String serialNumber, String uuid) {
        this.serialNumber = serialNumber;
        this.uuid = uuid;
    }

    public ItdSwatch(Long entityId, String serialNumber, String uuid, String barcode, String version, Double width, Double height, Double length) {
        this.entityId = entityId;
        this.serialNumber = serialNumber;
        this.uuid = uuid;
        this.barcode = barcode;
        this.version = version;
        this.width = width;
        this.height = height;
        this.length = length;
    }

    public ItdSwatch EntityfromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, ItdSwatch.class);
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
