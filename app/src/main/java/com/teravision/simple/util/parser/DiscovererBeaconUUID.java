package com.teravision.simple.util.parser;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by Edgard Rendon on 28/07/2016.
 */
public class DiscovererBeaconUUID {

    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public static UUID parseAdvertisementPacketToGetUUID(final byte[] scanRecord) {

        byte[] advertisedData = Arrays.copyOf(scanRecord, scanRecord.length);
        UUID uuids = null;
        int offset = 0;
        while (offset < (advertisedData.length - 2)) {
            int len = advertisedData[offset++];
            if (len == 0)
                break;

            int type = advertisedData[offset++];

            switch (type) {
                case 0x02: // Partial list of 16-bit UUIDs
                case 0x03: // Complete list of 16-bit UUIDs
                    while (len > 1) {
                        int uuid16 = advertisedData[offset++] & 0xFF;
                        uuid16 |= (advertisedData[offset++] << 8);
                        len -= 2;
                        uuids = UUID.fromString(String.format(
                                "%08x-0000-0000-0000-0000", uuid16));
                    }
                    break;
                case 0x06:// Partial list of 128-bit UUIDs
                case 0x07:// Complete list of 128-bit UUIDs
                    // Loop through the advertised 128-bit UUID's.
                    while (len >= 16) {
                        try {
                            // Wrap the advertised bits and order them.
                            ByteBuffer buffer = ByteBuffer.wrap(advertisedData,
                                    offset++, 16).order(ByteOrder.LITTLE_ENDIAN);
                            long mostSignificantBit = buffer.getLong();
                            long leastSignificantBit = buffer.getLong();
                            uuids = new UUID(leastSignificantBit,
                                    mostSignificantBit);
                        } catch (IndexOutOfBoundsException e) {
                            // Defensive programming.
                            System.out.printf("BlueToothDeviceFilter.parseUUID", e.toString());
                            continue;
                        } finally {
                            // Move the offset to read the next uuid.
                            offset += 15;
                            len -= 16;
                        }
                    }
                    break;
                case 0xFF:  // Manufacturer Specific Data
                    System.out.println("Manufacturer Specific Data size:" + len +" bytes" );
                    break;
                default:
                    offset += (len - 1);
                    break;
            }
        }
        return uuids;

    }
}
