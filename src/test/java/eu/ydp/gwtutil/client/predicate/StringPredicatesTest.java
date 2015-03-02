package eu.ydp.gwtutil.client.predicate;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class StringPredicatesTest {

	@Test
	public void notBlankEmptyStrings() throws Exception {
		List<String> listWithEmptyStrings = Lists.newArrayList("", "");
		Iterable<String> filtered = Iterables.filter(listWithEmptyStrings, StringPredicates.notBlank());
		assertEquals(Lists.newArrayList(filtered).size(), 0);
	}

	@Test
	public void notBlankNulls() throws Exception {
		List<String> listWithEmptyStrings = Lists.newArrayList(null, null);
		Iterable<String> filtered = Iterables.filter(listWithEmptyStrings, StringPredicates.notBlank());
		assertEquals(Lists.newArrayList(filtered).size(), 0);
	}

	@Test
	public void notBlankMixed() throws Exception {
		List<String> listWithEmptyStrings = Lists.newArrayList(null, null, "", "a", " ", "w");
		Iterable<String> filtered = Iterables.filter(listWithEmptyStrings, StringPredicates.notBlank());
		assertEquals(Lists.newArrayList(filtered).size(), 2);
	}

}
