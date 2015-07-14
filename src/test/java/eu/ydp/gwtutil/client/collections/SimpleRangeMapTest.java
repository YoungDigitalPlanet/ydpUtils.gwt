package eu.ydp.gwtutil.client.collections;

import com.google.common.collect.Range;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleRangeMapTest {

    @Test
    public void get() throws Exception {
        SimpleRangeMap<Integer, String> instance = SimpleRangeMap.<Integer, String>create();
        String value = "1-10";
        instance.put(Range.closed(1, 10), value);
        for (int x = 1; x <= 10; ++x) {
            assertEquals(value, instance.get(x));
        }
        for (int x = 11; x <= 100; ++x) {
            assertNull(instance.get(x));
        }

        for (int x = -100; x <= 0; ++x) {
            assertNull(instance.get(x));
        }

    }

    @Test
    public void getMultipleRange() throws Exception {
        SimpleRangeMap<Integer, String> instance = SimpleRangeMap.<Integer, String>create();
        String value1_10 = "1-10";
        instance.put(Range.closed(1, 10), value1_10);
        String value12_17 = "12-18";
        instance.put(Range.closedOpen(12, 18), value12_17);

        for (int x = 1; x <= 10; ++x) {
            assertEquals(value1_10, instance.get(x));
        }

        for (int x = 12; x <= 17; ++x) {
            assertEquals(value12_17, instance.get(x));
        }
        assertNull(instance.get(18));
        assertNull(instance.get(11));

    }

    @Test
    public void create() throws Exception {
        assertNotNull(SimpleRangeMap.<Integer, String>create());
    }

}
