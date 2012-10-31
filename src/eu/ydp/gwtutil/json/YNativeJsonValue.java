package eu.ydp.gwtutil.json;

import java.io.IOException;
import java.io.StringWriter;

import com.google.gwt.dev.json.JsonValue;

import eu.ydp.gwtutil.client.json.YJsonArray;
import eu.ydp.gwtutil.client.json.YJsonBoolean;
import eu.ydp.gwtutil.client.json.YJsonNumber;
import eu.ydp.gwtutil.client.json.YJsonObject;
import eu.ydp.gwtutil.client.json.YJsonString;
import eu.ydp.gwtutil.client.json.YJsonValue;

public class YNativeJsonValue implements YJsonValue {
	
	protected JsonValue jSonValue;
	
	public YNativeJsonValue(JsonValue value) {
		this.jSonValue = value;		
	}
	
	public JsonValue toJson(){
		return jSonValue;
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

	@Override
	public YJsonNumber isNumber() {
		if (this instanceof YJsonNumber)
			return (YJsonNumber)this;
		return null;
	}
	
	@Override
	public YJsonObject isObject() {
		if (this instanceof YJsonObject)
			return (YJsonObject)this;
		return null;
	}	
	
	@Override
	public YJsonBoolean isBoolean() {
		return (this instanceof YJsonBoolean) ? (YJsonBoolean)this : null;
	}
	
	@Override
	public String toString() {
		StringWriter writer = new StringWriter();
		try {
			jSonValue.write(writer);
			return writer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

}
