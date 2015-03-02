package eu.ydp.gwtutil.client.collections;

import java.util.Map;

import eu.ydp.gwtutil.category.GWTUnitTestCategory;
import org.junit.experimental.categories.Category;

import com.google.common.collect.Maps;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.junit.client.GWTTestCase;

@Category(GWTUnitTestCategory.class)
public class JsMapStringToIntConverterTest extends GWTTestCase {

	private static final String KEY_0 = "k0";
	private static final String KEY_1 = "k1";
	private static final Integer VALUE_0 = 10;
	private static final Integer VALUE_1 = 11;
	private final JsMapStringToIntConverter converter = new JsMapStringToIntConverter();

	@Override
	public String getModuleName() {
		return "eu.ydp.gwtutil.YdpGwtUtil";
	}

	public void testJsMapToMap() {
		// given
		JsMapStringToInt jsMap = JavaScriptObject.createObject().cast();
		jsMap.put(KEY_0, VALUE_0);
		jsMap.put(KEY_1, VALUE_1);

		// when
		Map<String, Integer> map = converter.toMap(jsMap);

		// then
		assertEquals(2, map.keySet().size());
		assertEquals(VALUE_0, map.get(KEY_0));
		assertEquals(VALUE_1, map.get(KEY_1));
	}

	public void testJsMapToMap_empty() {
		// given
		JsMapStringToInt jsMap = JavaScriptObject.createObject().cast();
		// when
		Map<String, Integer> map = converter.toMap(jsMap);

		// then
		assertEquals(0, map.keySet().size());
	}

	public void testMapToJsMap() {
		// given
		Map<String, Integer> map = Maps.newHashMap();
		map.put(KEY_0, VALUE_0);
		map.put(KEY_1, VALUE_1);

		// when
		MapStringToInt jsMap = converter.toJsMap(map);

		// then
		assertEquals(2, jsMap.keySet().size());
		assertEquals(VALUE_0, jsMap.get(KEY_0));
		assertEquals(VALUE_1, jsMap.get(KEY_1));
	}

	public void testMapToJsMap_empty() {
		// given
		Map<String, Integer> map = Maps.newHashMap();

		// when
		MapStringToInt jsMap = converter.toJsMap(map);

		// then
		assertEquals(0, jsMap.keySet().size());
	}

}
