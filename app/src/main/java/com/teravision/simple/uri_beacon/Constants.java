package com.teravision.simple.uri_beacon;

/**
 * Created by Sara Villarreal on 7/25/2016.
 */
public class Constants {
    private Constants() {
    }

    /**
     * Eddystone-UID frame type value.
     */
    public static final byte UID_FRAME_TYPE = 0x00;

    /**
     * Eddystone-URL frame type value.
     */
    public static final byte URL_FRAME_TYPE = 0x10;

    /**
     * Eddystone-TLM frame type value.
     */
    public static final byte TLM_FRAME_TYPE = 0x20;

    /**
     * Minimum expected Tx power (in dBm) in UID and URL frames.
     */
    public static final int MIN_EXPECTED_TX_POWER = -100;

    /**
     * Maximum expected Tx power (in dBm) in UID and URL frames.
     */
    public static final int MAX_EXPECTED_TX_POWER = 20;
}
