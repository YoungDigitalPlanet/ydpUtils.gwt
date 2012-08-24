package eu.ydp.gwtutil.client.json;


public interface YJsonValue {

	YJsonArray isArray();
	YJsonString isString();
	YJsonNumber isNumber();
}
