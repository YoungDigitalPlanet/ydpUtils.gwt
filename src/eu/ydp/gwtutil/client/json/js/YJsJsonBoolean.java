package eu.ydp.gwtutil.client.json.js;

import com.google.gwt.json.client.JSONBoolean;

import eu.ydp.gwtutil.client.json.YJsonBoolean;

public class YJsJsonBoolean extends YJsJsonValue implements YJsonBoolean {
	
	YJsJsonBoolean(JSONBoolean bool){
		super(bool);
	}
	
	@Override
	public boolean booleanValue() {
		return toJson().isBoolean().booleanValue();
	}
	
}
