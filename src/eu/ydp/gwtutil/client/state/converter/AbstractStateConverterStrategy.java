package eu.ydp.gwtutil.client.state.converter;

import eu.ydp.gwtutil.client.json.YJsonValue;
import eu.ydp.gwtutil.client.service.json.IJSONService;

public abstract class AbstractStateConverterStrategy implements IStateConvertionStrategy {
	
	protected IJSONService jsonService;
	
	public AbstractStateConverterStrategy(IJSONService jsonService ) {
		this.jsonService = jsonService;
	}
	
	public abstract YJsonValue convert(YJsonValue jsonState);
	

}
