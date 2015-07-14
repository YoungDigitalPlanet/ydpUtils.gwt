package eu.ydp.gwtutil.json;

import com.google.gwt.dev.json.JsonObject;
import com.google.gwt.dev.json.JsonValue;
import com.google.gwt.dev.json.Pair;
import eu.ydp.gwtutil.client.json.YJsonObject;
import eu.ydp.gwtutil.client.json.YJsonValue;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class YNativeJsonObject extends YNativeJsonValue implements YJsonObject {

    public YNativeJsonObject(JsonObject obj) {
        super(obj);
    }

    @Override
    public YJsonValue get(String key) {
        return YNativeJsonFactory.create(this.jSonValue.asObject().get(key));
    }

    @Override
    public Set<String> keySet() {
        Set<String> keys = new HashSet<String>();
        Iterator<Pair<String, JsonValue>> iterator = this.jSonValue.asObject().iterator();

        while (iterator.hasNext()) {
            keys.add(iterator.next().getA());
        }

        return keys;
    }

    @Override
    public void put(String key, YJsonValue value) {
        JsonValue val = ((YNativeJsonValue) value).toJson();
        this.jSonValue.asObject().put(key, val);

    }

    @Override
    public int size() {
        Set<String> keys = keySet();
        int res = (keys != null) ? keys.size() : 0;
        return res;
    }
}
