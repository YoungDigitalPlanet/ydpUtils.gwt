package eu.ydp.gwtutil.client.state;

import java.util.Collections;
import java.util.List;

import eu.ydp.gwtutil.client.json.YJsonObject;
import eu.ydp.gwtutil.client.json.YJsonValue;
import eu.ydp.gwtutil.client.state.converter.StateConverter;

public class StateImporter {
	
	List<StateConverter> stateConverters;

	public void setStateConverters(List<StateConverter> stateConverters) {
		this.stateConverters = stateConverters;		
		Collections.sort(this.stateConverters);
	}
	
	public YJsonValue importState(YJsonValue state, int toVersion){
		int versionState = getStateVersion(state);
		YJsonValue outState = state; 
		
		if(toVersion != versionState){
			List<StateConverter> converters = stateConverters.subList(versionState,toVersion);
			for(StateConverter converter: converters){
				outState = converter.convert(outState);
			}
		}
		
		return outState;
	
	}
	
	int getStateVersion(YJsonValue state){
		int version = 0;
		if(state instanceof YJsonObject){
			YJsonObject obj = (YJsonObject)state;
			YJsonValue jsonVer = obj.get(StateVersion.VERSION_FIELD);
			if(jsonVer != null && jsonVer.isNumber() != null){
				version = jsonVer.isNumber().numberValue().intValue();
			}
		}
		
		return version;
	}
	
	
		
	
}
