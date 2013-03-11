package eu.ydp.gwtutil.client.state.converter;

import eu.ydp.gwtutil.client.json.YJsonValue;

public class StateConverter implements Comparable<StateConverter> {
	
	IStateConvertionStrategy conversionStrategy;
	int version;
	
	public StateConverter(IStateConvertionStrategy strategy) {
		this.conversionStrategy = strategy;
		this.version= conversionStrategy.getVersion();
		
	}
	
	public YJsonValue convert(YJsonValue state){
		return conversionStrategy.convert(state);
	}

	public int getVersion() {
		return version;
	}	
	
	@Override
	public int compareTo(StateConverter converter) {
		return Integer.valueOf(version).compareTo(converter.getVersion());
	}
}
