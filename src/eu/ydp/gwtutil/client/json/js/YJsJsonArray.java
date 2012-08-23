package eu.ydp.gwtutil.client.json.js;

import com.google.gwt.json.client.JSONArray;

import eu.ydp.gwtutil.client.json.YJsonArray;
import eu.ydp.gwtutil.client.json.YJsonValue;

public class YJsJsonArray extends YJsJsonValue implements YJsonArray {

	public YJsJsonArray(JSONArray json) {
		super(json);
	}

	@Override
	public YJsonValue get(int index) {
		return YJsJsonFactory.create(toJson().isArray().get(index));
	}

	@Override
	public void set(int index, YJsonValue value) {
		toJson().isArray().set(index, value.toJson());
	}

	@Override
	public int size() {
		return toJson().isArray().size();
	}

}
