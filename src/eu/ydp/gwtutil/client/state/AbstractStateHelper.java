package eu.ydp.gwtutil.client.state;

import java.util.List;

import com.google.inject.Inject;

import eu.ydp.gwtutil.client.json.YJsonObject;
import eu.ydp.gwtutil.client.json.YJsonValue;
import eu.ydp.gwtutil.client.service.json.IJSONService;
import eu.ydp.gwtutil.client.state.converter.StateConverter;

public abstract class AbstractStateHelper {
	
	
	@Inject protected IJSONService jsonService;
	
	protected int version;
	
	
	public YJsonValue importState(YJsonValue inState){
		StateImporter stateImporter = new StateImporter();
		stateImporter.setStateConverters(prepareStateConverters());
						
		return stateImporter.importState(inState, version).isObject();
	}
	
	public YJsonValue exportState(YJsonValue outState){
		if(outState instanceof YJsonObject){
			((YJsonObject) outState).put(StateVersion.VERSION_FIELD, jsonService.createNumber(version));
		}
		
		return outState;
	}
	
	
	
	protected abstract List<StateConverter> prepareStateConverters();

	public int getVersion() {
		return version;
	}
	
}
