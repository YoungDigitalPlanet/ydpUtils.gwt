package eu.ydp.gwtutil.client.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import eu.ydp.gwtutil.client.collections.Matchee;


public class MatchableMap<K extends Matchable, V> implements Map<K, V> {

	private List<K> keys;
	private List<V> values;
	
	public MatchableMap(){
		keys = new ArrayList<K>();
		values = new ArrayList<V>();
	}
	
	@Override
	public void clear() {
		keys.clear();
		values.clear();
	}

	@Override
	public boolean containsKey(Object key) {
		if (!(key instanceof Matchee))
			return false;
		for (K k : keys)
			if (k.matches((Matchee)key))
				return true;
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		return values.contains(value);
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		Set<java.util.Map.Entry<K, V>> entries = new TreeSet<Map.Entry<K,V>>();
		for (int i = 0 ; i < keys.size() ; i ++){
			final K currK = keys.get(i);
			final V currV = values.get(i);
			entries.add(new Entry<K, V>() {

				private K key = currK;
				private V value = currV;
				
				@Override
				public K getKey() {
					return key;
				}

				@Override
				public V getValue() {
					return value;
				}

				@Override
				public V setValue(V arg0) {
					if (keys.indexOf(getKey()) != -1){
						value = arg0;
						return values.set(keys.indexOf(getKey()), arg0);
					}
					return null;
				}
			});
		}
		return null;
	}

	@Override
	public V get(Object key) {
		if (!(key instanceof Matchee))
			return null;
		for (int i = 0 ; i < keys.size() ; i ++)
			if (keys.get(i).matches((Matchee)key))
				return values.get(i);
		return null;
	}

	@Override
	public boolean isEmpty() {
		return keys.size() == 0;
	}

	@Override
	public Set<K> keySet() {
		return new TreeSet<K>(keys);
	}

	@Override
	public V put(K key, V value) {
		for (int i = 0 ; i < keys.size() ; i ++)
			if (keys.get(i).matches((Matchee)key))
				return values.set(i, value);
		keys.add(key);
		values.add(value);
		return value;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		Set<? extends K> keysSet = m.keySet();
		for (K k : keysSet){
			keys.add(k);
			values.add(m.get(k));
		}
	}

	@Override
	public V remove(Object key) {
		if (!(key instanceof Matchee))
			return null;
		for (int i = 0 ; i < keys.size() ; i ++)
			if (keys.get(i).matches((Matchee)key)){
				keys.remove(i);
				return values.remove(i);
			}
		return null;
	}

	@Override
	public int size() {
		return keys.size();
	}

	@Override
	public Collection<V> values() {
		return values;
	}

	
}
