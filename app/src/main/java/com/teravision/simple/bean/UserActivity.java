package com.teravision.simple.bean;

import com.google.gson.Gson;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * User Activity entity
 * 
 * @author leonardo
 *
 */
public class UserActivity implements Serializable {


	private Long entityId;
	private Long userId;
	private Long itdSwatchId;
	private String createdTimeUser;
	private Timestamp createdTimeServer;
	private Double vCapacitor;
	private Double vBattery;
	private Double lattitude;
	private Double longitude;
	private String phoneMake;
	private String phoneModel;
	private String osMake;
	private String osVersion;
	private String bleVersion;
	private String serialNumber;


	public UserActivity() {
	}

	public UserActivity(Long entityId, Long userId, Long itdSwatchId, String createdTimeUser, Timestamp createdTimeServer, Double vCapacitor, Double vBattery, Double lattitude, Double longitude, String phoneMake, String phoneModel, String osMake, String osVersion, String bleVersion) {
		this.entityId = entityId;
		this.userId = userId;
		this.itdSwatchId = itdSwatchId;
		this.createdTimeUser = createdTimeUser;
		this.createdTimeServer = createdTimeServer;
		this.vCapacitor = vCapacitor;
		this.vBattery = vBattery;
		this.lattitude = lattitude;
		this.longitude = longitude;
		this.phoneMake = phoneMake;
		this.phoneModel = phoneModel;
		this.osMake = osMake;
		this.osVersion = osVersion;
		this.bleVersion = bleVersion;
	}

	public User UserActivityfromJson(String json){
		Gson gson = new Gson();
		return gson.fromJson(json, User.class);
	}

	public String toJSON(){
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getItdSwatchId() {
		return itdSwatchId;
	}

	public void setItdSwatchId(Long itdSwatchId) {
		this.itdSwatchId = itdSwatchId;
	}

	public String getCreatedTimeUser() {
		return createdTimeUser;
	}

	public void setCreatedTimeUser(String createdTimeUser) {
		this.createdTimeUser = createdTimeUser;
	}

	public Timestamp getCreatedTimeServer() {
		return createdTimeServer;
	}

	public void setCreatedTimeServer(Timestamp createdTimeServer) {
		this.createdTimeServer = createdTimeServer;
	}

	public Double getvCapacitor() {
		return vCapacitor;
	}

	public void setvCapacitor(Double vCapacitor) {
		this.vCapacitor = vCapacitor;
	}

	public Double getvBattery() {
		return vBattery;
	}

	public void setvBattery(Double vBattery) {
		this.vBattery = vBattery;
	}

	public Double getLattitude() {
		return lattitude;
	}

	public void setLattitude(Double lattitude) {
		this.lattitude = lattitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getPhoneMake() {
		return phoneMake;
	}

	public void setPhoneMake(String phoneMake) {
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

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getBleVersion() {
		return bleVersion;
	}

	public void setBleVersion(String bleVersion) {
		this.bleVersion = bleVersion;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
}
