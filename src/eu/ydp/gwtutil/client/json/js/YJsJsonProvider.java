package eu.ydp.gwtutil.client.json.js;

import eu.ydp.gwtutil.client.json.YJsonArray;
import eu.ydp.gwtutil.client.json.YJsonProvider;
import eu.ydp.gwtutil.client.json.YJsonString;

public class YJsJsonProvider implements YJsonProvider {

	@Override
	public YJsonArray createYJsonArray() {
		return YJsJsonFactory.createArray();
	}

	@Override
	public YJsonString createString(String text) {
		return YJsJsonFactory.createString(text);
	}

}
