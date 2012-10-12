package eu.ydp.gwtutil.client.collections;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@SuppressWarnings("serial")
public class StackMap<K, V> extends LinkedHashMap<K, V> {
	
	public List<K> getKeys(){
		return new ArrayList<K>(keySet());
	}

	public List<V> getValues() {
		return new ArrayList<V>(values());
	}
	
}
