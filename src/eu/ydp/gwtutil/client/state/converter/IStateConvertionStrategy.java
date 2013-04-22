package eu.ydp.gwtutil.client.state.converter;

import eu.ydp.gwtutil.client.json.YJsonValue;

public interface IStateConvertionStrategy {
	YJsonValue convert(YJsonValue jsonState);

	int getStartVersion();
}
