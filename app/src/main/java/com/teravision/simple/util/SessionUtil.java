package com.teravision.simple.util;

import com.teravision.simple.bean.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sara Villarreal on 8/1/2016.
 */
public abstract class SessionUtil {

    public static User user;

    public static Set<Integer> listSerialNumber;

    public static HashMap<Integer, byte[]> advertisementSend;


    static{
        listSerialNumber = new HashSet<>();
        advertisementSend = new HashMap<>();
    }
}
