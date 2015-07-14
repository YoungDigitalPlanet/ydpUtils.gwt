package eu.ydp.gwtutil.client.service.json;

import eu.ydp.gwtutil.client.json.*;
import eu.ydp.gwtutil.json.YNativeJsonFactory;
import eu.ydp.gwtutil.json.YNativeJsonParser;

public class NativeJSONService implements IJSONService {

    @Override
    public YJsonArray createArray() {
        return YNativeJsonFactory.createArray();
    }

    @Override
    public YJsonValue createNumber(double value) {
        return YNativeJsonFactory.createNumber(value);
    }

    @Override
    public YJsonObject createObject() {
        return YNativeJsonFactory.createObject();
    }

    @Override
    public YJsonString createString(String v) {
        return YNativeJsonFactory.createString(v);
    }

    @Override
    public YJsonBoolean createBoolean(boolean bool) {
        return YNativeJsonFactory.createBoolean(bool);
    }

    @Override
    public YJsonValue parse(String contents) {
        return YNativeJsonParser.parse(contents);
    }

}
