package eu.ydp.gwtutil.client.collections;

import static org.junit.Assert.assertFalse;

import java.util.Arrays;

import org.junit.Test;

public class RandomizedSetTest {
	@Test
	public void randomSetTest() {
		// prepare

		RandomizedSet<Integer> randomizedSet = new RandomizedSet<Integer>();
		int size = 10;
		Integer[] source = new Integer[size], result = new Integer[size];
		for (int x = 0; x < size; ++x) {
			source[x] = x;
			randomizedSet.push(Integer.valueOf(x));
		}
		for (int x = 0; x < size; ++x) {
			result[x] = randomizedSet.pull();
		}
		// test
		assertFalse(Arrays.equals(source, result));
	}
}
