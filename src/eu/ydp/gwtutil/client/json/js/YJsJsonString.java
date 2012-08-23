package eu.ydp.gwtutil.client.json.js;

import com.google.gwt.json.client.JSONString;

import eu.ydp.gwtutil.client.json.YJsonString;

public class YJsJsonString extends YJsJsonValue implements YJsonString {

	public YJsJsonString(JSONString json){
		super(json);
	}

	@Override
	public String stringValue() {
		return toJson().isString().stringValue();
	}
}
