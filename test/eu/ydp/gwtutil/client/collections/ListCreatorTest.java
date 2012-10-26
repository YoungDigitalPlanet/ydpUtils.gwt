package eu.ydp.gwtutil.client.collections;

import java.util.List;
import java.util.Vector;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import eu.ydp.gwtutil.AbstractTest;

public class ListCreatorTest extends AbstractTest {

	@Test
	public void testCreate(){
		List<Integer> list = ListCreator.create(2).add(3).add(4).build();
		assertThat(list.size(), equalTo(3));
		assertThat(list, contains(2, 3, 4));
	}

	@Test
	public void testImpl(){
		List<Integer> list = ListCreator.create(new Vector<Integer>()).add(2).add(3).add(4).build();
		assertThat(list.size(), equalTo(3));
		assertThat(list, contains(2, 3, 4));
		assertThat(list.getClass().getName(), equalTo(Vector.class.getName()));
	}
}
