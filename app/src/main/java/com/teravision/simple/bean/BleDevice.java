package com.teravision.simple.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sara Villarreal on 7/27/2016.
 */
public class BleDevice implements Serializable {
    private int userId;
    private int itdSwatchId;
    private Date createdTimeUser;
    private Float vCapacitor;
    private Float vBattery;
    private Float lattitude;
    private Float longitude;
    private Float phoneMake;
    private String phoneModel;
    private String osMake;
    private Float osVersion;
    private Float bleVersion;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getItdSwatchId() {
        return itdSwatchId;
    }

    public void setItdSwatchId(int itdSwatchId) {
        this.itdSwatchId = itdSwatchId;
    }

    public Date getCreatedTimeUser() {
        return createdTimeUser;
    }

    public void setCreatedTimeUser(Date createdTimeUser) {
        this.createdTimeUser = createdTimeUser;
    }

    public Float getvCapacitor() {
        return vCapacitor;
    }

    public void setvCapacitor(Float vCapacitor) {
        this.vCapacitor = vCapacitor;
    }

    public Float getvBattery() {
        return vBattery;
    }

    public void setvBattery(Float vBattery) {
        this.vBattery = vBattery;
    }

    public Float getLattitude() {
        return lattitude;
    }

    public void setLattitude(Float lattitude) {
        this.lattitude = lattitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getPhoneMake() {
        return phoneMake;
    }

    public void setPhoneMake(Float phoneMake) {
        this.phoneMake = phoneMake;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

    public String getOsMake() {
        return osMake;
    }

    public void setOsMake(String osMake) {
        this.osMake = osMake;
    }

    public Float getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(Float osVersion) {
        this.osVersion = osVersion;
    }

    public Float getBleVersion() {
        return bleVersion;
    }

    public void setBleVersion(Float bleVersion) {
        this.bleVersion = bleVersion;
    }





}
