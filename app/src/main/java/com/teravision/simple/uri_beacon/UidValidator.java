package com.teravision.simple.uri_beacon;

import android.util.Log;

import com.teravision.simple.util.Utils;

import java.util.Arrays;

/**
 * Created by Sara Villarreal on 7/25/2016.
 */
public class UidValidator {
    private static final String TAG = UidValidator.class.getSimpleName();

    private UidValidator() {
    }

    public static void validate(String deviceAddress, byte[] serviceData, Beacon beacon) {
        beacon.hasUidFrame = true;

        // Tx power should have reasonable values.
        int txPower = (int) serviceData[1];
        beacon.uidStatus.txPower = txPower;
        if (txPower < Constants.MIN_EXPECTED_TX_POWER || txPower > Constants.MAX_EXPECTED_TX_POWER) {
            String err = String
                    .format("Expected UID Tx power between %d and %d, got %d", Constants.MIN_EXPECTED_TX_POWER,
                            Constants.MAX_EXPECTED_TX_POWER, txPower);
            beacon.uidStatus.errTx = err;
            logDeviceError(deviceAddress, err);
        }

        // The namespace and instance bytes should not be all zeroes.
        byte[] uidBytes = Arrays.copyOfRange(serviceData, 2, 18);
        beacon.uidStatus.uidValue = Utils.toHexString(uidBytes);
        if (Utils.isZeroed(uidBytes)) {
            String err = "UID bytes are all 0x00";
            beacon.uidStatus.errUid = err;
            logDeviceError(deviceAddress, err);
        }

        // If we have a previous frame, verify the ID isn't changing.
        if (beacon.uidServiceData == null) {
            beacon.uidServiceData = serviceData.clone();
        } else {
            byte[] previousUidBytes = Arrays.copyOfRange(beacon.uidServiceData, 2, 18);
            if (!Arrays.equals(uidBytes, previousUidBytes)) {
                String err = String.format("UID should be invariant.\nLast: %s\nthis: %s",
                        Utils.toHexString(previousUidBytes),
                        Utils.toHexString(uidBytes));
                beacon.uidStatus.errUid = err;
                logDeviceError(deviceAddress, err);
                beacon.uidServiceData = serviceData.clone();
            }
        }

        // Last two bytes in frame are RFU and should be zeroed.
        byte[] rfu = Arrays.copyOfRange(serviceData, 18, 20);
        if (rfu[0] != 0x00 || rfu[1] != 0x00) {
            String err = "Expected UID RFU bytes to be 0x00, were " + Utils.toHexString(rfu);
            beacon.uidStatus.errRfu = err;
            logDeviceError(deviceAddress, err);
        }
    }

    private static void logDeviceError(String deviceAddress, String err) {
        Log.e(TAG, deviceAddress + ": " + err);
    }
}
