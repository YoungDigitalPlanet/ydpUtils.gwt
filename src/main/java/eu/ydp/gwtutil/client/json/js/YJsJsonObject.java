package eu.ydp.gwtutil.client.json.js;

import com.google.gwt.json.client.JSONObject;
import eu.ydp.gwtutil.client.json.YJsonObject;
import eu.ydp.gwtutil.client.json.YJsonValue;

import java.util.Set;

public class YJsJsonObject extends YJsJsonValue implements YJsonObject {

    YJsJsonObject(JSONObject json) {
        super(json);
    }

    @Override
    public YJsonValue get(String key) {
        return YJsJsonFactory.create(toJson().isObject().get(key));
    }

    @Override
    public void put(String key, YJsonValue value) {
        toJson().isObject().put(key, ((YJsJsonValue) value).toJson());
    }

    @Override
    public int size() {
        return toJson().isObject().size();
    }

    @Override
    public Set<String> keySet() {
        return toJson().isObject().keySet();
    }

}
