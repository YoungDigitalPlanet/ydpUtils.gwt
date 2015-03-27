package eu.ydp.gwtutil.client.json.js;

import com.google.gwt.json.client.JSONArray;

import eu.ydp.gwtutil.client.json.YJsonArray;

public class YJsJsonConverter {

	public JSONArray toJson(YJsonArray yJsonArray) {
		return (JSONArray) ((YJsJsonArray) yJsonArray).toJson();
	}

	public YJsonArray toYJson(JSONArray jsonArray) {
		return YJsJsonFactory.createArray(jsonArray);
	}
}
