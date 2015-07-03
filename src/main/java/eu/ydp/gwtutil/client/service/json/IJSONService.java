package eu.ydp.gwtutil.client.service.json;

import eu.ydp.gwtutil.client.json.*;

public interface IJSONService {

    YJsonValue parse(String contents);

    YJsonArray createArray();

    YJsonString createString(String value);

    YJsonValue createNumber(double value);

    YJsonObject createObject();

    YJsonBoolean createBoolean(boolean bool);

}
