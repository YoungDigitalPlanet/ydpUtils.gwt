package eu.ydp.gwtutil.json;

import com.google.gwt.dev.json.JsonString;

import eu.ydp.gwtutil.client.json.YJsonString;

public class YNativeJsonString extends YNativeJsonValue implements YJsonString {

	public YNativeJsonString(JsonString num) {
		super(num);
	}

	@Override
	public String stringValue() {
		return this.jSonValue.asString().getString();
	}

}
