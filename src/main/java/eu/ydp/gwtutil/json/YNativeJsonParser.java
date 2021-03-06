package eu.ydp.gwtutil.json;

import com.google.gwt.dev.json.*;
import eu.ydp.gwtutil.client.json.YJsonValue;

import java.io.IOException;
import java.io.StringReader;

public class YNativeJsonParser {

    public static YJsonValue parse(String contents) {

        JsonValue val;

        val = tryParseObject(contents);
        if (val == null) {
            val = tryParseAsArray(contents);
        }

        if (val == null) {
            val = parseAsNumberOrString(contents);
        }

        return YNativeJsonFactory.create(val);

    }

    private static JsonValue tryParseObject(String contents) {
        JsonValue val;
        StringReader sr = new StringReader(contents);
        try {
            val = JsonObject.parse(sr);
        } catch (JsonException e) {
            return null;
        } catch (IOException e) {
            return null;
        }

        return val;

    }

    private static JsonValue tryParseAsArray(String contents) {
        JsonValue val;
        StringReader sr = new StringReader(contents);
        try {
            val = JsonArray.parse(sr);
        } catch (JsonException e) {
            return null;
        } catch (IOException e) {
            return null;
        }

        return val;
    }

    private static JsonValue parseAsNumberOrString(String contents) {
        double num;
        JsonValue val;
        try {
            num = Double.parseDouble(contents);
            val = JsonNumber.create(num);
        } catch (NumberFormatException e) {
            val = JsonString.create(contents);
        }

        return val;

    }
}
