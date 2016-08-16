package com.teravision.simple.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sara Villarreal on 7/27/2016.
 */
public class APIResponse <T> implements Serializable {
    private Integer code;
    private String message;
    private ArrayList<T> entities;
    private Map<String,List> groupEntity;
    


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<T> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<T> entities) {
        this.entities = entities;
    }

    public Map<String, List> getGroupEntity() {
        if(groupEntity == null){
            groupEntity = new HashMap<>();
        }
        return groupEntity;
    }

    public void setGroupEntity(Map<String, List> groupEntity) {
        this.groupEntity = groupEntity;
    }
}
