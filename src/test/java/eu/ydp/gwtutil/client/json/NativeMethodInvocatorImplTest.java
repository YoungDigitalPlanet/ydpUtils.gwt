package eu.ydp.gwtutil.client.json;

import com.google.gwt.json.client.JSONParser;
import com.google.gwt.junit.client.GWTTestCase;
import eu.ydp.gwtutil.category.GWTUnitTestCategory;
import org.junit.experimental.categories.Category;

@Category(GWTUnitTestCategory.class)
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
