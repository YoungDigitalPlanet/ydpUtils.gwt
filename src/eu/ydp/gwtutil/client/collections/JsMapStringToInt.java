package eu.ydp.gwtutil.client.collections;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;

public class JsMapStringToInt extends JavaScriptObject implements MapStringToInt{

	protected JsMapStringToInt() {}
	
	@Override
	public final List<String> keys() {
		JsArrayString jsKeys = jsKeys();
		List<String> keys = Lists.newArrayList();
		
		for(int i=0; i<jsKeys.length(); i++) {
			String key = jsKeys.get(i);
			keys.add(key);
		}
		
		return keys;
	}
	
	public final native JsArrayString jsKeys() /*-{
		 return Object.keys(this);
	}-*/;

	@Override
	public final List<Integer> values() {
		List<Integer> values = Lists.newArrayList();
		List<String> keys = keys();
		
		for (String key : keys) {
			Integer value = get(key);
			values.add(value);
		}
		
		return values;
	}

	@Override
	public final Integer get(String key) {
		int value = getValue(key);
		return value;
	}
	
	public final native int getValue(String key) /*-{
		return this[key];
	}-*/;

	@Override
	public final void put(String key, Integer value) {
		putValue(key, value);
	}
	
	public final native void putValue(String key, int value) /*-{
		this[key] = value;
	}-*/;
}
