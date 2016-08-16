package com.teravision.simple.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.teravision.simple.bean.UserResult;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Sara Villarreal on 7/25/2016.
 */
public class Utils {

    private static final char[] HEX = "0123456789ABCDEF".toCharArray();
    public static String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private static Double capacitor = 0.0;
    private static Double battery = 0.0;



    public static String toHexString(byte[] bytes) {
        if (bytes.length == 0) {
            return "";
        }
        char[] chars = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            int c = bytes[i] & 0xFF;
            chars[i * 2] = HEX[c >>> 4];
            chars[i * 2 + 1] = HEX[c & 0x0F];
        }
        return new String(chars).toLowerCase();
    }

    public static boolean isZeroed(byte[] bytes) {
        for (byte b : bytes) {
            if (b != 0x00) {
                return false;
            }
        }
        return true;
    }

    public static int byteArray4ToInt(byte[] b, int start) {
        return b[3 + start] & 0xFF |
                (b[2 + start] & 0xFF) << 8 |
                (b[1 + start] & 0xFF) << 16 |
                (b[0 + start] & 0xFF) << 24;
    }

    public static double hexToVoltaje(byte _one, byte _two) {
        DecimalFormat df = new DecimalFormat("#.######");

        double result = 0;
        int one, two;

        one = _one < 0 ? 256 + _one : _one;
        two = _two < 0 ? 256 + _two : _two;

        result = one*256 + two;
        result = ((result) / 4096) * 4.3;

        result = Double.valueOf(df.format(result));
        return result;
    }

    public static boolean isValidEmail(String email) {
        if (email.matches(emailPattern))
            return true;
        return false;
    }


    public static boolean isAdvertisementAvailableToSend(Integer _serialNumber, byte[] _advertisement){
        boolean result, capacitorIsSame, batteryIsSame;
        byte[] lastAdvertisement;

        result = Boolean.TRUE;

        lastAdvertisement = SessionUtil.advertisementSend.get(_serialNumber);
        if(lastAdvertisement == null){
            SessionUtil.advertisementSend.put(_serialNumber,_advertisement);
        }else{
            capacitorIsSame = (lastAdvertisement[8] == _advertisement[8]) && (lastAdvertisement[9] == _advertisement[9]);
            batteryIsSame = (lastAdvertisement[10] == _advertisement[10]) && (lastAdvertisement[11] == _advertisement[11]);
            if (capacitorIsSame && batteryIsSame) {
                result = Boolean.FALSE;
            }else{
                SessionUtil.advertisementSend.put(_serialNumber,_advertisement);
            }
        }
        return result;
    }

    public static void savePreferences(Context context, String patron, boolean booleanTosave){
        SharedPreferences sp = context.getSharedPreferences(patron, context.MODE_WORLD_READABLE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putBoolean(patron, booleanTosave);
        ed.commit();
    }

    public static boolean getPreferences(Context context,String patron){

        SharedPreferences prefs = context.getSharedPreferences(patron, 0);
        boolean risEnabled = prefs.getBoolean(patron, true);
        if(risEnabled == true){

            return true;

        }else{
            return false;
        }

    }

    public static void getCurrentSerialNumberSwatch (Object groupEntity){
        ArrayList<UserResult> userProducts = new ArrayList<>();
        UserResult[] userArray = JsonUtil.getEntityFromJSON(toJSON(groupEntity), UserResult[].class);
        userProducts.addAll(Arrays.asList(userArray));
        for (UserResult userProduct : userProducts){
            SessionUtil.listSerialNumber.add(Integer.valueOf(userProduct.getItdSwatch().getSerialNumber()));
        }
    }

    public static String toJSON(Object entity){
        Gson gson = new Gson();
        return gson.toJson(entity);
    }

}
