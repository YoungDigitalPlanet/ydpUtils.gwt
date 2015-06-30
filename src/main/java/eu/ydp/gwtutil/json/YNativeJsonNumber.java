package eu.ydp.gwtutil.json;

import com.google.gwt.dev.json.JsonNumber;
import eu.ydp.gwtutil.client.json.YJsonNumber;

public class YNativeJsonNumber extends YNativeJsonValue implements YJsonNumber {

    public YNativeJsonNumber(JsonNumber str) {
        super(str);
    }

    @Override
    public Double numberValue() {
        return this.jSonValue.asNumber().getDecimal();

    }

}
