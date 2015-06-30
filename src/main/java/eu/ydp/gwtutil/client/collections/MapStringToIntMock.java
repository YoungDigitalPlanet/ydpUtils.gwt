package eu.ydp.gwtutil.client.collections;

import java.util.*;

public class MapStringToIntMock implements MapStringToInt {

    private final Map<String, Integer> map = new HashMap<String, Integer>();

    @Override
    public Set<String> keySet() {
        return new HashSet<String>(map.keySet());
    }

    @Override
    public List<Integer> values() {
        return new ArrayList<Integer>(map.values());
    }

    @Override
    public Integer get(String key) {
        return map.get(key);
    }

    @Override
    public void put(String key, Integer value) {
        map.put(key, value);
    }

    @Override
    public boolean containsKey(String key) {
        return map.containsKey(key);
    }

}
