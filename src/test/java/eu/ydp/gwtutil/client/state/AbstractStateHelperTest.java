package eu.ydp.gwtutil.client.state;

import eu.ydp.gwtutil.client.json.YJsonArray;
import eu.ydp.gwtutil.client.json.YJsonNumber;
import eu.ydp.gwtutil.client.json.YJsonValue;
import eu.ydp.gwtutil.client.service.json.IJSONService;
import eu.ydp.gwtutil.client.service.json.NativeJSONService;
import eu.ydp.gwtutil.client.state.converter.StateConverter;
import eu.ydp.gwtutil.json.YNativeJsonFactory;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class AbstractStateHelperTest {

    private IJSONService jsonService = new NativeJSONService();

    @Test
    public void testImportState() {
        // given
        AbstractStateHelper helper = mock(AbstractStateHelper.class);
        YJsonValue inState = YNativeJsonFactory.createObject();
        when(helper.importState(inState)).thenCallRealMethod();
        when(helper.prepareStateConverters()).thenReturn(new ArrayList<StateConverter>());

        // when
        YJsonValue outState = helper.importState(inState);

        // then
        assertThat(inState, equalTo(outState));

    }

    @Test
    @Parameters(method = "exportStateParams")
    public void testExportState(YJsonValue inState) {
        // given
        AbstractStateHelper helper = mock(AbstractStateHelper.class);
        helper.jsonService = jsonService;
        when(helper.exportState(inState)).thenCallRealMethod();
        when(helper.getTargetVersion()).thenReturn(0);

        // when
        YJsonValue exportedState = helper.exportState(inState);

        // then
        if (inState instanceof YJsonArray) {
            assertThat(exportedState, equalTo(inState));
        } else {
            YJsonNumber version = exportedState.isObject().get(StateVersion.VERSION_FIELD).isNumber();
            assertThat(version.numberValue().intValue(), equalTo(helper.getTargetVersion()));
        }

    }

    @SuppressWarnings("unused")
    private Object[] exportStateParams() {
        return $($(YNativeJsonFactory.createObject()), $(YNativeJsonFactory.createArray()));
    }

}
