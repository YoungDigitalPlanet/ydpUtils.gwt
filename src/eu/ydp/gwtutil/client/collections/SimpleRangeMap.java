package eu.ydp.gwtutil.client.collections;

import java.util.Map;

import com.google.common.collect.Maps;
import com.google.common.collect.Range;

public class SimpleRangeMap<K extends Comparable<?>, V> {
	private final Map<Range<K>, V> mapWithRange = Maps.newHashMap();

	public void put(Range<K> range, V value) {
		mapWithRange.put(range, value);
	}

	public V get(K key) {
		for (Map.Entry<Range<K>, V> entry : mapWithRange.entrySet()) {
			if (entry.getKey().contains(key)) {
				return entry.getValue();
			}
		}
		return null;
	}

	public static <K extends Comparable<?>, V> SimpleRangeMap<K, V> create(){
		return new SimpleRangeMap<K, V>();
	}
}
