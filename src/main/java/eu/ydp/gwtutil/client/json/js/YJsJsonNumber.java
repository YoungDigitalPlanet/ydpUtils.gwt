package eu.ydp.gwtutil.client.json.js;

import com.google.gwt.json.client.JSONNumber;
import eu.ydp.gwtutil.client.json.YJsonNumber;

public class YJsJsonNumber extends YJsJsonValue implements YJsonNumber {

    YJsJsonNumber(JSONNumber json) {
        super(json);
    }

    @Override
    public Double numberValue() {
        return toJson().isNumber().doubleValue();
    }

}
