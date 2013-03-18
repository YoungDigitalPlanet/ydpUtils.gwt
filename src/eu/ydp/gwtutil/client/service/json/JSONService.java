package eu.ydp.gwtutil.client.service.json;

import eu.ydp.gwtutil.client.json.YJsonArray;
import eu.ydp.gwtutil.client.json.YJsonBoolean;
import eu.ydp.gwtutil.client.json.YJsonObject;
import eu.ydp.gwtutil.client.json.YJsonString;
import eu.ydp.gwtutil.client.json.YJsonValue;
import eu.ydp.gwtutil.client.json.js.YJsJsonFactory;
import eu.ydp.gwtutil.client.json.js.YJsJsonParser;

public class JSONService implements IJSONService {

	public YJsonValue parse(String contents){
		return YJsJsonParser.parse(contents);
	}
	
	public YJsonArray createArray(){
		return YJsJsonFactory.createArray();
	}
	
	public YJsonString createString(String value){
		return YJsJsonFactory.createString(value);
	}

	public YJsonValue createNumber(double value) {
		return YJsJsonFactory.createNumber(value);
	}

	public YJsonObject createObject() {
		return YJsJsonFactory.createObject();
	}	
	
	public YJsonBoolean createBoolean(boolean bool) {
		return YJsJsonFactory.createBoolean(bool);
	}
}