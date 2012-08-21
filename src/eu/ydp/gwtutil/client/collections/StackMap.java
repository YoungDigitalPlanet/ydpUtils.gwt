package eu.ydp.gwtutil.client.collections;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class StackMap<K, V>  {
	
	private static final long serialVersionUID = -5203287630904024114L;

	protected List<K> keys;
	protected List<V> values;

	public StackMap(){
		super();
		keys = new ArrayList<K>();
		values = new ArrayList<V>();
	}


	public V put(K key, V value){
		keys.add(key);
		values.add(value);
		return value;
	}
	
	public void clear(){
		keys.clear();
		values.clear();
	}
	
	public List<K> getKeys(){
		return keys;
	}
	
	public List<V> getValues(){
		return values;
	}
	
	public boolean containsKey(K key){
		return keys.contains(key);
	}
	
	public V get(K key){
		int index = keys.indexOf(key);
		return values.get(index);
	}
	
	public Set<K> keySet(){
		return new HashSet<K>(keys);
	}
	
}
