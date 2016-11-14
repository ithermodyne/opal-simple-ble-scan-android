package concept.test.app.com.simple;

import java.text.DecimalFormat;

/**
 * Created by Edgard Rendon on 13/11/2016.
 */

public class Utils {

    public static int byteArray4ToInt(byte[] b, int start) {
        return b[3 + start] & 0xFF |
                (b[2 + start] & 0xFF) << 8 |
                (b[1 + start] & 0xFF) << 16 |
                (b[start] & 0xFF) << 24;
    }

    public static double hexToVoltaje(byte _one, byte _two) {
        DecimalFormat df = new DecimalFormat("#.######");

        double result = 0;
        int one, two;

        one = _one < 0 ? 256 + _one : _one;
        two = _two < 0 ? 256 + _two : _two;

        result = one * 256 + two;
        result = ((result) / 4096) * 4.3;

        result = Double.valueOf(df.format(result));
        return result;
    }

}
