package com.teravision.simple.uri_beacon;

import android.util.Log;

import com.teravision.simple.util.UrlUtils;
import com.teravision.simple.util.Utils;

import java.util.Arrays;

/**
 * Created by Sara Villarreal on 7/25/2016.
 */
public class UrlValidator {
    private static final String TAG = UrlValidator.class.getSimpleName();

    private UrlValidator() {
    }

    public static void validate(String deviceAddress, byte[] serviceData, Beacon beacon) {
        beacon.hasUrlFrame = true;

        // Tx power should have reasonable values.
        int txPower = (int) serviceData[1];
        if (txPower < Constants.MIN_EXPECTED_TX_POWER || txPower > Constants.MAX_EXPECTED_TX_POWER) {
            String err = String.format("Expected URL Tx power between %d and %d, got %d",
                    Constants.MIN_EXPECTED_TX_POWER, Constants.MAX_EXPECTED_TX_POWER, txPower);
            beacon.urlStatus.txPower = err;
            logDeviceError(deviceAddress, err);
        }

        // The URL bytes should not be all zeroes.
        byte[] urlBytes = Arrays.copyOfRange(serviceData, 2, 20);
        if (Utils.isZeroed(urlBytes)) {
            String err = "URL bytes are all 0x00";
            beacon.urlStatus.urlNotSet = err;
            logDeviceError(deviceAddress, err);
        }

        beacon.urlStatus.urlValue = UrlUtils.decodeUrl(serviceData);
    }

    private static void logDeviceError(String deviceAddress, String err) {
        Log.e(TAG, deviceAddress + ": " + err);
    }
}
