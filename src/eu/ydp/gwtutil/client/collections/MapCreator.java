package eu.ydp.gwtutil.client.collections;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public final class MapCreator<K, V> {

	private Map<K, V> map;
	
	private MapCreator(){
		map = new HashMap<K, V>();
	}
	
	private MapCreator(Map<K, V> map){
		this.map = map;
	}
	
	public static <K, V> MapCreator<K, V> create(Map<K, V> map){
		MapCreator<K, V> mc = new MapCreator<K, V>(map);
		return mc;
	}
	
	public static <K, V> MapCreator<K, V> create(K key, V value){
		MapCreator<K, V> mc = new MapCreator<K, V>();
		mc.map.put(key, value);
		return mc;
	}
	
	public MapCreator<K, V> put(K key, V value){
		map.put(key, value);
		return this;
	}
	
	public Map<K, V> build(){
		return map;
	}
	
}
