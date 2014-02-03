package eu.ydp.gwtutil.client.service.json;

import eu.ydp.gwtutil.client.json.YJsonArray;
import eu.ydp.gwtutil.client.json.YJsonBoolean;
import eu.ydp.gwtutil.client.json.YJsonObject;
import eu.ydp.gwtutil.client.json.YJsonString;
import eu.ydp.gwtutil.client.json.YJsonValue;

public interface IJSONService {

	YJsonValue parse(String contents);

	YJsonArray createArray();

	YJsonString createString(String value);

	YJsonValue createNumber(double value);

	YJsonObject createObject();

	YJsonBoolean createBoolean(boolean bool);

}
