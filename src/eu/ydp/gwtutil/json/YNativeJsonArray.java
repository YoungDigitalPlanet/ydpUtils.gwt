package eu.ydp.gwtutil.json;

import com.google.gwt.dev.json.JsonArray;

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
		for(i = 0; i < currArry.getLength(); i++ ){
			if(i != index){
				setArray.add(currArry.get(i));
			}
			else{
				setArray.add(((YNativeJsonValue)value).toJson());
			}		
		}
		
		this.jSonValue = setArray;
	}

	@Override
	public int size() {
		return this.jSonValue.asArray().getLength();
	}

}
