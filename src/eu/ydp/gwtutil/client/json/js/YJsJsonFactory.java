package eu.ydp.gwtutil.client.json.js;

import com.google.gwt.json.client.JSONValue;

import eu.ydp.gwtutil.client.json.YJsonValue;

public final class YJsJsonFactory {

	private YJsJsonFactory(){}
	
	public static YJsonValue create(JSONValue json){
		if (json.isArray() != null){
			return new YJsJsonArray(json.isArray());
		}
		if (json.isString() != null){
			return new YJsJsonString(json.isString());
		}
		return new YJsJsonValue(json);
	}
}
