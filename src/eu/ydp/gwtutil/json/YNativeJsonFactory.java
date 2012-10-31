package eu.ydp.gwtutil.json;

import com.google.gwt.dev.json.JsonArray;
import com.google.gwt.dev.json.JsonBoolean;
import com.google.gwt.dev.json.JsonNumber;
import com.google.gwt.dev.json.JsonObject;
import com.google.gwt.dev.json.JsonString;
import com.google.gwt.dev.json.JsonValue;

import eu.ydp.gwtutil.client.json.YJsonNumber;
import eu.ydp.gwtutil.client.json.YJsonValue;

public class YNativeJsonFactory {
	private YNativeJsonFactory() {};	
	
	public static YJsonValue create(JsonValue json){
		if (json.isArray()){
			return new YNativeJsonArray(json.asArray());
		}
		
		if (json.isString()){
			return new YNativeJsonString(json.asString());
		}
		
		if (json.isNumber()){
			return new YNativeJsonNumber(json.asNumber());
		}
		
		if (json.isObject()){
			return new YNativeJsonObject(json.asObject());
		}
		
		if(json.isBoolean()){
			return new YNativeJsonBoolean(json.asBoolean());
		}
		
		return new YNativeJsonValue(json);
	}
	
	public static YNativeJsonArray createArray(){
		return new YNativeJsonArray(JsonArray.create());
	}
	
	public static YNativeJsonString createString(String v){		
		return new YNativeJsonString(JsonString.create(v));		
	}	

	public static YJsonNumber createNumber(double value) {
		return new YNativeJsonNumber(JsonNumber.create(value));
	}
	
	public static YNativeJsonObject createObject() {
		return new YNativeJsonObject(new JsonObject());
	}
	
	public static YNativeJsonBoolean createBoolean(boolean bool){
		return new YNativeJsonBoolean(JsonBoolean.create(bool));
	}
}
