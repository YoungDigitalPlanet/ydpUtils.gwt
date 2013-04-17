package eu.ydp.gwtutil.json;

import eu.ydp.gwtutil.client.json.YJsonArray;
import eu.ydp.gwtutil.client.json.YJsonProvider;
import eu.ydp.gwtutil.client.json.YJsonString;

public class YNativeJsonProvider implements YJsonProvider {

	@Override
	public YJsonArray createYJsonArray() {
		return YNativeJsonFactory.createArray();
	}

	@Override
	public YJsonString createString(String text) {
		return YNativeJsonFactory.createString(text);
	}

}
