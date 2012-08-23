package eu.ydp.gwtutil.client.json;

import com.google.gwt.json.client.JSONValue;

public interface YJsonValue {

	YJsonArray isArray();
	YJsonString isString();
	JSONValue toJson();
}
