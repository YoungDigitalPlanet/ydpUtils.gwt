package eu.ydp.gwtutil.client.json.js;

import com.google.gwt.json.client.JSONValue;

import eu.ydp.gwtutil.client.json.YJsonArray;
import eu.ydp.gwtutil.client.json.YJsonString;
import eu.ydp.gwtutil.client.json.YJsonValue;

public class YJsJsonValue implements YJsonValue {

	private JSONValue json;

	public YJsJsonValue(JSONValue json){
		this.json = json;
	}
	
	public JSONValue toJson(){
		return json;
	}

	@Override
	public YJsonArray isArray() {
		if (this instanceof YJsonArray)
			return (YJsonArray)this;
		return null;
	}

	@Override
	public YJsonString isString() {
		if (this instanceof YJsonString)
			return (YJsonString)this;
		return null;
	}
}
