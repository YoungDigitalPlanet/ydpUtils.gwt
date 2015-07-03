package eu.ydp.gwtutil.client.service.json;

import eu.ydp.gwtutil.client.json.*;
import eu.ydp.gwtutil.client.json.js.YJsJsonFactory;
import eu.ydp.gwtutil.client.json.js.YJsJsonParser;

public class JSONService implements IJSONService {

    @Override
    public YJsonValue parse(String contents) {
        return YJsJsonParser.parse(contents);
    }

    @Override
    public YJsonArray createArray() {
        return YJsJsonFactory.createArray();
    }

    @Override
    public YJsonString createString(String value) {
        return YJsJsonFactory.createString(value);
    }

    @Override
    public YJsonValue createNumber(double value) {
        return YJsJsonFactory.createNumber(value);
    }

    @Override
    public YJsonObject createObject() {
        return YJsJsonFactory.createObject();
    }

    @Override
    public YJsonBoolean createBoolean(boolean bool) {
        return YJsJsonFactory.createBoolean(bool);
    }
}
