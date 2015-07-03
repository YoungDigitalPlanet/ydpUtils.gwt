package eu.ydp.gwtutil.client.json.js;

import com.google.gwt.json.client.JSONParser;
import eu.ydp.gwtutil.client.json.YJsonValue;

public final class YJsJsonParser {

    private YJsJsonParser() {
    }

    public static YJsonValue parse(String contents) {
        return YJsJsonFactory.create(JSONParser.parseStrict(contents));
    }
}
