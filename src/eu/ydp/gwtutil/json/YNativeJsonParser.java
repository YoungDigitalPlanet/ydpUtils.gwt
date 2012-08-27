package eu.ydp.gwtutil.json;


import java.io.IOException;
import java.io.StringReader;

import com.google.gwt.dev.json.JsonArray;
import com.google.gwt.dev.json.JsonException;
import com.google.gwt.dev.json.JsonNumber;
import com.google.gwt.dev.json.JsonObject;
import com.google.gwt.dev.json.JsonString;
import com.google.gwt.dev.json.JsonValue;

import eu.ydp.gwtutil.client.json.YJsonValue;


public class YNativeJsonParser {
	
	public static YJsonValue parse(String contents){	
		
		JsonValue val;
		
		val = parseAsObject(contents);
		if(val == null)
			val = parseAsArray(contents);
		else
			val = parseAsNumberOrString(contents);
		
		return YNativeJsonFactory.create(val);
		
	}
	
	
	private static JsonValue parseAsObject(String contents){
		JsonValue val;
		StringReader sr = new StringReader(contents);
		try{
			val = JsonObject.parse(sr);
		}
		catch (JsonException e) {
			return null;
		}
		catch(IOException e){
			return null;
		}
		
		return val;	
		
	}
	
	private static JsonValue parseAsArray(String contents){
		JsonValue val;
		StringReader sr = new StringReader(contents);
		try{
			val = JsonArray.parse(sr);
		}
		catch (JsonException e) {
			return null;
		}
		catch(IOException e){
			return null;
		}
		
		return val;
	}
	
	private static JsonValue parseAsNumberOrString(String contents){
		double num;
		JsonValue val;
		try{
			num = Double.parseDouble(contents);
			val = JsonNumber.create(num);
		}
		catch(NumberFormatException e){
			val = JsonString.create(contents);
		}
		
		return val;
		
	}
}
