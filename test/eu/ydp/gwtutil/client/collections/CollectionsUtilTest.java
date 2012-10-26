package eu.ydp.gwtutil.client.collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import eu.ydp.gwtutil.AbstractTestBase;

public class CollectionsUtilTest extends AbstractTestBase {

	@Test
	public void testIndexOfNot1(){
		List<String> list = ListCreator.create("a").add("b").add("a").build();
		assertThat(CollectionsUtil.indexOfNot(list, null), equalTo(0));
		assertThat(CollectionsUtil.indexOfNot(list, ""), equalTo(0));
		assertThat(CollectionsUtil.indexOfNot(list, "a"), equalTo(1));
		assertThat(CollectionsUtil.indexOfNot(list, "b"), equalTo(0));
		assertThat(CollectionsUtil.indexOfNot(list, "x"), equalTo(0));
	}
	
	@Test
	public void testIndexOfNot2(){
		List<String> list2 = ListCreator.create(new ArrayList<String>()).add(null).add("").add("a").add("b").build();
		assertThat(CollectionsUtil.indexOfNot(list2, null), equalTo(1));
		assertThat(CollectionsUtil.indexOfNot(list2, ""), equalTo(0));
		assertThat(CollectionsUtil.indexOfNot(list2, "a"), equalTo(0));
		assertThat(CollectionsUtil.indexOfNot(list2, "b"), equalTo(0));
		assertThat(CollectionsUtil.indexOfNot(list2, "x"), equalTo(0));
	}
	
	@Test
	public void testIndexOfNot3(){
		List<String> list3 = ListCreator.create("").add(null).add("a").add("b").build();
		assertThat(CollectionsUtil.indexOfNot(list3, null), equalTo(0));
		assertThat(CollectionsUtil.indexOfNot(list3, ""), equalTo(1));
		assertThat(CollectionsUtil.indexOfNot(list3, "a"), equalTo(0));
		assertThat(CollectionsUtil.indexOfNot(list3, "b"), equalTo(0));
		assertThat(CollectionsUtil.indexOfNot(list3, "x"), equalTo(0));
	}
	
	@Test
	public void testIndexOfNot4(){
		List<String> list4 = ListCreator.create("").add("").add("").add("").build();
		assertThat(CollectionsUtil.indexOfNot(list4, null), equalTo(0));
		assertThat(CollectionsUtil.indexOfNot(list4, ""), equalTo(-1));
		assertThat(CollectionsUtil.indexOfNot(list4, "a"), equalTo(0));
		assertThat(CollectionsUtil.indexOfNot(list4, "b"), equalTo(0));
		assertThat(CollectionsUtil.indexOfNot(list4, "x"), equalTo(0));
		
	}
	
	@Test
	public void testIndexOfNot5(){
		List<String> list4 = ListCreator.create(new ArrayList<String>()).add(null).add(null).add(null).add(null).build();
		assertThat(CollectionsUtil.indexOfNot(list4, null), equalTo(-1));
		assertThat(CollectionsUtil.indexOfNot(list4, ""), equalTo(0));
		assertThat(CollectionsUtil.indexOfNot(list4, "a"), equalTo(0));
		assertThat(CollectionsUtil.indexOfNot(list4, "b"), equalTo(0));
		assertThat(CollectionsUtil.indexOfNot(list4, "x"), equalTo(0));
		
	}
	
}
