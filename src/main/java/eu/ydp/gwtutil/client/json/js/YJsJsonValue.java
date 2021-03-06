package eu.ydp.gwtutil.client.json.js;

import com.google.gwt.json.client.JSONValue;
import eu.ydp.gwtutil.client.json.*;

public class YJsJsonValue implements YJsonValue {

    private JSONValue json;

    YJsJsonValue(JSONValue json) {
        this.json = json;
    }

    public JSONValue toJson() {
        return json;
    }

    @Override
    public YJsonArray isArray() {
        if (this instanceof YJsonArray)
            return (YJsonArray) this;
        return null;
    }

    @Override
    public YJsonString isString() {
        if (this instanceof YJsonString)
            return (YJsonString) this;
        return null;
    }

    @Override
    public YJsonNumber isNumber() {
        if (this instanceof YJsonNumber)
            return (YJsonNumber) this;
        return null;
    }

    @Override
    public YJsonObject isObject() {
        if (this instanceof YJsonObject)
            return (YJsonObject) this;
        return null;
    }

    @Override
    public YJsonBoolean isBoolean() {
        return (this instanceof YJsonBoolean) ? (YJsonBoolean) this : null;
    }

    @Override
    public String toString() {
        String str = "";

        if (json != null) {
            str = json.toString();
        }

        return str;

    }
}
