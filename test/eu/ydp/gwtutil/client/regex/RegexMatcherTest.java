package eu.ydp.gwtutil.client.regex;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RegexMatcherTest {
	@Test
	public void matchTest() {
		RegexMatcher matcher = new RegexMatcher();
		assertTrue(matcher.matches("abbbbcd", ""));
		assertTrue(matcher.matches("abbbbcd", "ab*bcd"));
		assertTrue(matcher.matches("abbbbcd", "b*"));
		assertTrue(matcher.matches("abbbbcd", "ab+cd"));
		assertTrue(matcher.matches("abbbbcd", "b+"));
		assertTrue(matcher.matches("abbbbcd", "ab+bcd"));
		assertTrue(matcher.matches("abbbbcd", "ab*bcd"));

		assertTrue(matcher.matches("abbbbcd", "ab+cd"));
		assertTrue(matcher.matches("abbbbcd", "a.*d"));
		assertTrue(matcher.matches("abbbbcd", "ab+bcd"));
		assertFalse(matcher.matches("abbbbcd", "a.*e"));

		assertTrue(matcher.matches("abbbbcd", "a.*d"));
		assertTrue(matcher.matches("", "(|none)"));
		assertFalse(matcher.matches("abbbbcd", "a.*e"));
		assertTrue(matcher.matches("none", "(|none)"));
		assertTrue(matcher.matches("none", "^(|none)$"));
		assertFalse(matcher.matches("abbbbcd", "^b*$"));

	}
}
