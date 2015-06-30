package eu.ydp.gwtutil.client.collections;

import com.google.common.collect.Maps;
import com.google.gwt.core.client.JavaScriptObject;

import java.util.Map;

public class JsMapStringToIntConverter {

    public JsMapStringToInt toJsMap(Map<String, Integer> map) {
        JsMapStringToInt jsMap = JavaScriptObject.createObject().cast();
        for (String key : map.keySet()) {
            jsMap.put(key, map.get(key));
        }
        return jsMap;
    }

    public Map<String, Integer> toMap(MapStringToInt jsMap) {
        Map<String, Integer> map = Maps.newHashMap();
        for (String key : jsMap.keySet()) {
            map.put(key, jsMap.get(key));
        }
        return map;
    }
}
