package eu.ydp.gwtutil.json;

import com.google.gwt.dev.json.JsonArray;
import com.google.gwt.dev.json.JsonValue;

import eu.ydp.gwtutil.client.json.YJsonArray;
import eu.ydp.gwtutil.client.json.YJsonValue;

public class YNativeJsonArray extends YNativeJsonValue implements YJsonArray {	
	
	
	public YNativeJsonArray(JsonArray arr) {
		super(arr);
	}
	
	
	@Override
	public YJsonValue get(int index) {		
		return YNativeJsonFactory.create(this.jSonValue.asArray().get(index));		
	}

	@Override
	public void set(int index, YJsonValue value) {
		//toJson().isArray().set(index, ((YJsJsonValue)value).toJson());
		JsonArray setArray = new JsonArray();
		JsonArray currArry = toJson().asArray();
		int i;
		for(i = 0; i < currArry.getLength()  ||  i <= index ; i++ ){
			if(i == index){
				setArray.add(((YNativeJsonValue)value).toJson());
			} else if(i >= currArry.getLength()){
				setArray.add((JsonValue)null);
			} else {
				setArray.add(currArry.get(i));
			}		
		}
		
		this.jSonValue = setArray;
	}

	@Override
	public int size() {
		return this.jSonValue.asArray().getLength();
	}

}
