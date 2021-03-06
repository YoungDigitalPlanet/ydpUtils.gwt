package eu.ydp.gwtutil.client.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BooleanUtilsTest {

    BooleanUtils instance = new BooleanUtils();

    @Test
    public void testGetBoolean() {
        boolean attribute = instance.getBoolean("xxx");
        assertEquals(false, attribute);
        attribute = instance.getBoolean("false");
        assertEquals(false, attribute);
        attribute = instance.getBoolean("true");
        assertEquals(true, attribute);
        attribute = instance.getBoolean("0");
        assertEquals(false, attribute);
        attribute = instance.getBoolean("1");
        assertEquals(true, attribute);
        attribute = instance.getBoolean("1.0");
        assertEquals(false, attribute);
    }

}
