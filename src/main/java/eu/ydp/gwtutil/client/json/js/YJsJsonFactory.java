package eu.ydp.gwtutil.client.json.js;

import com.google.gwt.json.client.*;
import eu.ydp.gwtutil.client.json.*;

public final class YJsJsonFactory {

    private YJsJsonFactory() {
    }

    public static YJsonValue create(JSONValue json) {
        if (json.isArray() != null) {
            return new YJsJsonArray(json.isArray());
        }
        if (json.isString() != null) {
            return new YJsJsonString(json.isString());
        }
        if (json.isNumber() != null) {
            return new YJsJsonNumber(json.isNumber());
        }
        if (json.isObject() != null) {
            return new YJsJsonObject(json.isObject());
        }
        if (json.isBoolean() != null) {
            return new YJsJsonBoolean(json.isBoolean());
        }
        return new YJsJsonValue(json);
    }

    public static YJsonArray createArray() {
        return new YJsJsonArray(new JSONArray());
    }

    public static YJsonArray createArray(JSONArray jsonArray) {
        return new YJsJsonArray(jsonArray);
    }

    public static YJsonString createString(String v) {
        return new YJsJsonString(new JSONString(v));
    }

    public static YJsonValue createNumber(double value) {
        return new YJsJsonNumber(new JSONNumber(value));
    }

    public static YJsonObject createObject() {
        return new YJsJsonObject(new JSONObject());
    }

    public static YJsonBoolean createBoolean(boolean bool) {
        return new YJsJsonBoolean(JSONBoolean.getInstance(bool));
    }
}
