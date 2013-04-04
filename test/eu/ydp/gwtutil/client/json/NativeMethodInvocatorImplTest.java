package eu.ydp.gwtutil.client.json;

import com.google.gwt.json.client.JSONParser;
import com.google.gwt.junit.client.GWTTestCase;

public class NativeMethodInvocatorImplTest extends GWTTestCase {

	public void testCallMethod() {
		TestObject testObject = (TestObject) JSONParser.parseLenient("{exec:false}").isObject().getJavaScriptObject();
		testObject.init();
		NativeMethodInvocatorImpl invocator = new NativeMethodInvocatorImpl();
		invocator.callMethod(testObject, TestObject.METHOD_NAME);
		assertTrue(testObject.isExec());
	}

	@Override
	public String getModuleName() {
		return "eu.ydp.gwtutil.YdpGwtUtil";
	}

}
