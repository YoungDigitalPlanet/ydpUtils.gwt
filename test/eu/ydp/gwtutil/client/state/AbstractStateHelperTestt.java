package eu.ydp.gwtutil.client.state;

import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import eu.ydp.gwtutil.AbstractTestBase;
import eu.ydp.gwtutil.client.json.YJsonArray;
import eu.ydp.gwtutil.client.json.YJsonNumber;
import eu.ydp.gwtutil.client.json.YJsonValue;
import eu.ydp.gwtutil.client.service.json.IJSONService;
import eu.ydp.gwtutil.client.state.converter.StateConverter;
import eu.ydp.gwtutil.json.YNativeJsonFactory;

@RunWith(JUnitParamsRunner.class)
public class AbstractStateHelperTestt extends AbstractTestBase {
	
	
	private IJSONService jsonService;
	protected AbstractStateHelper helper;
	
	@Override	
	public void setUp() {
		
		super.setUp();
		jsonService = injector.getInstance(IJSONService.class);
	}
	
	@Test
	public void testImportState(){
		//given
		AbstractStateHelper helper = mock(AbstractStateHelper.class);
		YJsonValue inState = YNativeJsonFactory.createObject();		
		when(helper.importState(inState)).thenCallRealMethod();
		when(helper.prepareStateConverters()).thenReturn(new ArrayList<StateConverter>());
		
		//when
		YJsonValue outState = helper.importState(inState);
		
		//then
		assertThat(inState, equalTo(outState));		
		
	}	
	
	@Test
	@Parameters(method="exportStateParams")
	public void testExportState(YJsonValue inState){
		//given
		AbstractStateHelper helper = mock(AbstractStateHelper.class);		
		helper.jsonService = jsonService;
		when(helper.exportState(inState)).thenCallRealMethod();
		when(helper.getVersion()).thenCallRealMethod();
		
		//when
		YJsonValue exportedState = helper.exportState(inState);
		
		//then
		if(inState instanceof YJsonArray){
			assertThat(exportedState, equalTo(inState));
		}
		else{
			YJsonNumber version = exportedState.isObject().get(StateVersion.VERSION_FIELD).isNumber();
			assertThat(version.numberValue().intValue(), equalTo(helper.getVersion()));
		}
		
	}
	
	@SuppressWarnings("unused")
	private Object[] exportStateParams() {		
        return $(
                     $(YNativeJsonFactory.createObject()),
                     $(YNativeJsonFactory.createArray())                     
                );
    }
	
	@Test
	public void testPrepareStateConverters(){	
		
		//when
		List<StateConverter> converters = helper.prepareStateConverters();
		Collections.sort(converters);
		//then
		assertThat(converters.size(), equalTo(helper.getVersion()));
		
		for(int i = 0; i < converters.size(); i++){
			assertThat(converters.get(i).getVersion(), equalTo(i));
		}
		
	}

}
