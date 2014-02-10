package eu.ydp.gwtutil.client.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This Map implementation is intentionally based on List as HashMap implementation contains bug and can't be used with some JavaScriptObjects as keys.
 * 
 * @author rrybacki
 * 
 */
public class StackMap<K, V> implements Map<K, V> {

	protected List<K> keys;
	protected List<V> values;

	public StackMap() {
		super();
		keys = new ArrayList<K>();
		values = new ArrayList<V>();
	}

	@Override
	public V put(K key, V value) {
		int index = keys.indexOf(key);
		if (index == -1) {
			keys.add(key);
			values.add(value);
		} else {
			keys.set(index, key);
			values.set(index, value);
		}
		return value;
	}

	@Override
	public void clear() {
		keys.clear();
		values.clear();
	}

	public List<K> getKeys() {
		return new ArrayList<K>(keys);
	}

	public List<V> getValues() {
		return new ArrayList<V>(values);
	}

	@Override
	public boolean containsKey(Object key) {
		return keys.contains(key);
	}

	@Override
	public V get(Object key) {
		int index = keys.indexOf(key);
		if (index == -1) {
			return null;
		} else {
			return values.get(index);
		}
	}

	@Override
	public Set<K> keySet() {
		return new HashSet<K>(keys);
	}

	@Override
	public V remove(Object key) {
		int index = keys.indexOf(key);
		if (index != -1) {
			keys.remove(index);
			V val = values.remove(index);
			return val;
		}
		return null;
	}

	@Override
	public boolean containsValue(Object arg0) {
		return values.contains(arg0);
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		throw new UnsupportedOperationException("StackMap.entrySet is not supported");
	}

	@Override
	public boolean isEmpty() {
		return keys.isEmpty();
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> arg0) {
		throw new UnsupportedOperationException("StackMap.putAll is not supported");
	}

	@Override
	public int size() {
		return keys.size();
	}

	@Override
	public Collection<V> values() {
		return new ArrayList<V>(values);
	}

}
