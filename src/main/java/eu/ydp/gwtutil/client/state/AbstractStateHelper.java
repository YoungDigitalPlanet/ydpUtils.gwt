package eu.ydp.gwtutil.client.state;

import com.google.inject.Inject;
import eu.ydp.gwtutil.client.json.YJsonObject;
import eu.ydp.gwtutil.client.json.YJsonValue;
import eu.ydp.gwtutil.client.service.json.IJSONService;
import eu.ydp.gwtutil.client.state.converter.StateConverter;

import java.util.List;

public abstract class AbstractStateHelper {

    @Inject
    protected IJSONService jsonService;

    public YJsonValue importState(YJsonValue inState) {
        StateImporter stateImporter = new StateImporter();
        stateImporter.setStateConverters(prepareStateConverters());

        return stateImporter.importState(inState, getTargetVersion());
    }

    public YJsonValue exportState(YJsonValue outState) {
        if (outState instanceof YJsonObject) {
            ((YJsonObject) outState).put(StateVersion.VERSION_FIELD, jsonService.createNumber(getTargetVersion()));
        }

        return outState;
    }

    protected abstract List<StateConverter> prepareStateConverters();

    public abstract int getTargetVersion();

}
