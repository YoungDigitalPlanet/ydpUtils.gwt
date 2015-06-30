package eu.ydp.gwtutil.client.json;

import java.util.Set;

public interface YJsonObject extends YJsonValue {

    YJsonValue get(String key);

    void put(String key, YJsonValue value);

    int size();

    Set<String> keySet();
}
