package eu.ydp.gwtutil.client.collections;

import com.google.common.collect.Lists;
import org.fest.assertions.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CollectionsUtilTest {

    @Test
    public void testIndexOfNot1() {
        List<String> list = ListCreator.create("a").add("b").add("a").build();
        assertThat(CollectionsUtil.indexOfNot(list, null), equalTo(0));
        assertThat(CollectionsUtil.indexOfNot(list, ""), equalTo(0));
        assertThat(CollectionsUtil.indexOfNot(list, "a"), equalTo(1));
        assertThat(CollectionsUtil.indexOfNot(list, "b"), equalTo(0));
        assertThat(CollectionsUtil.indexOfNot(list, "x"), equalTo(0));
    }

    @Test
    public void testIndexOfNot2() {
        List<String> list2 = ListCreator.create(new ArrayList<String>()).add(null).add("").add("a").add("b").build();
        assertThat(CollectionsUtil.indexOfNot(list2, null), equalTo(1));
        assertThat(CollectionsUtil.indexOfNot(list2, ""), equalTo(0));
        assertThat(CollectionsUtil.indexOfNot(list2, "a"), equalTo(0));
        assertThat(CollectionsUtil.indexOfNot(list2, "b"), equalTo(0));
        assertThat(CollectionsUtil.indexOfNot(list2, "x"), equalTo(0));
    }

    @Test
    public void testIndexOfNot3() {
        List<String> list3 = ListCreator.create("").add(null).add("a").add("b").build();
        assertThat(CollectionsUtil.indexOfNot(list3, null), equalTo(0));
        assertThat(CollectionsUtil.indexOfNot(list3, ""), equalTo(1));
        assertThat(CollectionsUtil.indexOfNot(list3, "a"), equalTo(0));
        assertThat(CollectionsUtil.indexOfNot(list3, "b"), equalTo(0));
        assertThat(CollectionsUtil.indexOfNot(list3, "x"), equalTo(0));
    }

    @Test
    public void testIndexOfNot4() {
        List<String> list4 = ListCreator.create("").add("").add("").add("").build();
        assertThat(CollectionsUtil.indexOfNot(list4, null), equalTo(0));
        assertThat(CollectionsUtil.indexOfNot(list4, ""), equalTo(-1));
        assertThat(CollectionsUtil.indexOfNot(list4, "a"), equalTo(0));
        assertThat(CollectionsUtil.indexOfNot(list4, "b"), equalTo(0));
        assertThat(CollectionsUtil.indexOfNot(list4, "x"), equalTo(0));

    }

    @Test
    public void testIndexOfNot5() {
        List<String> list4 = ListCreator.create(new ArrayList<String>()).add(null).add(null).add(null).add(null).build();
        assertThat(CollectionsUtil.indexOfNot(list4, null), equalTo(-1));
        assertThat(CollectionsUtil.indexOfNot(list4, ""), equalTo(0));
        assertThat(CollectionsUtil.indexOfNot(list4, "a"), equalTo(0));
        assertThat(CollectionsUtil.indexOfNot(list4, "b"), equalTo(0));
        assertThat(CollectionsUtil.indexOfNot(list4, "x"), equalTo(0));

    }

    @Test
    public void testContainsAnyOfElements_shouldReturnTrue() throws Exception {
        List<String> elements = Lists.newArrayList("element1", "element2");
        List<String> collection = Lists.newArrayList("sth", "sth2", "element2");

        boolean containsAnyOfElements = CollectionsUtil.containsAnyOfElements(elements, collection);
        assertTrue(containsAnyOfElements);
    }

    @Test
    public void testContainsAnyOfElements_shouldReturnFalse() throws Exception {
        List<String> elements = Lists.newArrayList("element1", "element2");
        List<String> collection = Lists.newArrayList("sth", "sth2", "sth3");

        boolean containsAnyOfElements = CollectionsUtil.containsAnyOfElements(elements, collection);
        assertFalse(containsAnyOfElements);
    }

    @Test
    public void shouldReturnRangeIntList() {
        // given
        int lower = -3;
        int upper = 5;
        Integer[] expectedIntegers = {-3, -2, -1, 0, 1, 2, 3, 4, 5};

        // when
        List<Integer> resultList = CollectionsUtil.getRangeList(lower, upper);

        // then
        Assertions.assertThat(resultList).containsExactly(expectedIntegers);
    }
}
