package eu.ydp.gwtutil.client.util;

import com.google.inject.Singleton;

@Singleton
public class BooleanUtils {
    public boolean getBoolean(String value) {
        boolean returnValue = false;
        if (value != null) {
            try {
                returnValue = Integer.parseInt(value) == 1;
            } catch (Exception exception) {
                returnValue = Boolean.parseBoolean(value);
            }
        }
        return returnValue;
    }
}
