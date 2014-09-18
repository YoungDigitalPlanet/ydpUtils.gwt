package eu.ydp.gwtutil.client.collections;

import java.util.List;
import java.util.Set;

import eu.ydp.gwtutil.category.GWTUnitTestCategory;
import org.junit.experimental.categories.Category;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.junit.client.GWTTestCase;

@Category(GWTUnitTestCategory.class)
public class JsMapStringToIntTest extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "eu.ydp.gwtutil.YdpGwtUtil";
	}

	public void testShouldReturnInsertedValue() throws Exception {
		JsMapStringToInt mapStringToInt = JavaScriptObject.createObject().cast();

		String key = "key";
		Integer value = 34;
		mapStringToInt.put(key, value);
		mapStringToInt.put("sth", 1312);

		Integer result = mapStringToInt.get(key);
		assertEquals(value, result);
	}

	public void testShouldReturnKeysOfAllInsertedEntries() throws Exception {
		JsMapStringToInt mapStringToInt = JavaScriptObject.createObject().cast();

		mapStringToInt.put("key1", 1);
		mapStringToInt.put("key2", 2);
		mapStringToInt.put("key3", 3);

		Set<String> keys = mapStringToInt.keySet();
		assertEquals(3, keys.size());
		assertTrue(keys.contains("key1"));
		assertTrue(keys.contains("key2"));
		assertTrue(keys.contains("key3"));
	}

	public void testShouldReturnValuesOfAllInsertedEntries() throws Exception {
		JsMapStringToInt mapStringToInt = JavaScriptObject.createObject().cast();

		mapStringToInt.put("key1", 1);
		mapStringToInt.put("key2", 2);
		mapStringToInt.put("key3", 3);

		List<Integer> values = mapStringToInt.values();
		assertEquals(3, values.size());
		assertTrue(values.contains(1));
		assertTrue(values.contains(2));
		assertTrue(values.contains(3));
	}
}
