package eu.ydp.gwtutil.client.json.js;

import com.google.gwt.json.client.JSONArray;
import com.google.inject.Singleton;
import eu.ydp.gwtutil.client.json.YJsonArray;

@Singleton
public class YJsJsonConverter {

    public JSONArray toJson(YJsonArray yJsonArray) {
        return (JSONArray) ((YJsJsonArray) yJsonArray).toJson();
    }

    public YJsonArray toYJson(JSONArray jsonArray) {
        return YJsJsonFactory.createArray(jsonArray);
    }
}
