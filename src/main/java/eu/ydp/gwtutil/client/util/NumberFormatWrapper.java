package eu.ydp.gwtutil.client.util;

import com.google.gwt.i18n.client.NumberFormat;

public class NumberFormatWrapper {

    public String formatNumber(String pattern, int value) {
        return NumberFormat.getFormat(pattern).format(value);
    }
}
