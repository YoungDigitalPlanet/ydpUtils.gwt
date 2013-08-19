package eu.ydp.gwtutil.client.collections;

import java.util.List;

public interface MapStringToInt {

	List<String> keys();
	
	List<Integer> values();
	
	Integer get(String key);
	
	void put(String key, Integer value);
}
