package eu.ydp.gwtutil.client.collections;

import org.junit.Test;

import java.util.List;
import java.util.Vector;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

public class ListCreatorTest {

    @Test
    public void testCreate() {
        List<Integer> list = ListCreator.create(2).add(3).add(4).build();
        assertThat(list.size(), equalTo(3));
        assertThat(list, contains(2, 3, 4));
    }

    @Test
    public void testImpl() {
        List<Integer> list = ListCreator.create(new Vector<Integer>()).add(2).add(3).add(4).build();
        assertThat(list.size(), equalTo(3));
        assertThat(list, contains(2, 3, 4));
        assertThat(list.getClass().getName(), equalTo(Vector.class.getName()));
    }
}
