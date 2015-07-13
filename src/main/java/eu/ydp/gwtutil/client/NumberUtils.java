package eu.ydp.gwtutil.client;

import java.util.List;

public class NumberUtils {

    public static Integer tryParseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
        }
        return 0;
    }

    public static Integer tryParseInt(String s, Integer defaultValue) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
        }
        return defaultValue;
    }

    public static Integer tryParseInt(String s, int radix, Integer defaultValue) {
        try {
            return Integer.parseInt(s, radix);
        } catch (Exception e) {
        }
        return defaultValue;
    }

    public static Float tryParseFloat(String s) {
        try {
            return Float.parseFloat(s);
        } catch (Exception e) {
        }
        return 0f;
    }

    public static Double tryParseDouble(String s) {
        try {
            return Double.parseDouble(s);
        } catch (Exception e) {
        }
        return 0d;
    }

    public static Double tryParseDouble(String s, Double defaultValue) {
        try {
            return Double.parseDouble(s);
        } catch (Exception e) {
        }
        return defaultValue;
    }

    public static Integer listSum(List<Integer> values) {
        Integer sum = 0;
        for (Integer v : values) {
            sum += v;
        }
        return sum;
    }
}
