package eu.ydp.gwtutil.client.json.js;

import java.util.Set;

import com.google.gwt.json.client.JSONObject;

import eu.ydp.gwtutil.client.json.YJsonObject;
import eu.ydp.gwtutil.client.json.YJsonValue;

public class YJsJsonObject extends YJsJsonValue implements YJsonObject {

	public YJsJsonObject(JSONObject json) {
		super(json);
	}

	@Override
	public YJsonValue get(String key) {
		return YJsJsonFactory.create(toJson().isObject().get(key));
	}

	@Override
	public void put(String key, YJsonValue value) {
		toJson().isObject().put(key, value.toJson());
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
