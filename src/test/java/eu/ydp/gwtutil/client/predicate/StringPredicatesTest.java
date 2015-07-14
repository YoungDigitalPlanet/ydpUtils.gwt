package eu.ydp.gwtutil.client.predicate;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

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
