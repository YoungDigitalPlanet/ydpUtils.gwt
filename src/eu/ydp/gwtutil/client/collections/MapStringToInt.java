package eu.ydp.gwtutil.client.collections;

import java.util.List;
import java.util.Set;

public interface MapStringToInt {

	Set<String> keySet();
	
	List<Integer> values();
	
	Integer get(String key);
	
	boolean containsKey(String key);
	
	void put(String key, Integer value);
}
