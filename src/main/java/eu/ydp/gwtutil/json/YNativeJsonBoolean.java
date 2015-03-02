package eu.ydp.gwtutil.json;

import com.google.gwt.dev.json.JsonBoolean;

import eu.ydp.gwtutil.client.json.YJsonBoolean;

public class YNativeJsonBoolean extends YNativeJsonValue implements YJsonBoolean {

	public YNativeJsonBoolean(JsonBoolean bool) {
		super(bool);
	}

	@Override
	public boolean booleanValue() {
		return this.jSonValue.asBoolean().getBoolean();

	}
}
