package eu.ydp.gwtutil.client.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MultiMapUtil {
    public static <K, V> void multimapAppend(Map<K, List<V>> src, Map<K, List<V>> dst) {
        for (Map.Entry<K, List<V>> srce : src.entrySet()) {
            List<V> refs = dst.get(srce.getKey());
            if (refs == null) {
                refs = new ArrayList<V>();
                dst.put(srce.getKey(), refs);
            }

            refs.addAll(srce.getValue());
        }
    }

    public static <K, V> void multimapAdd(K cId, Map<K, List<V>> map, V element) {
        List<V> refs = map.get(cId);
        if (refs == null) {
            refs = new ArrayList<V>();
            map.put(cId, refs);
        }

        refs.add(element);
    }
}
