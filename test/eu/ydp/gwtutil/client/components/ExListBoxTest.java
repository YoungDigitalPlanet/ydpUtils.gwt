package eu.ydp.gwtutil.client.components;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwtmockito.GwtMockitoTestRunner;

import eu.ydp.gwtutil.client.components.exlistbox.ExListBox;
import eu.ydp.gwtutil.client.components.exlistbox.ExListBoxDelays;

@RunWith(GwtMockitoTestRunner.class)
public class ExListBoxTest {
	private ExListBox testObj;

	@Before
	public void setUp() {
		ExListBoxDelays delays = new ExListBoxDelays() {

			@Override
			public int getCloseDelay() {
				return 0;
			}

			@Override
			public int getAutoHideDelay() {
				return 0;
			}
		};

		testObj = new ExListBox(delays);

		testObj.addOption(new InlineHTML(), new InlineHTML());
		testObj.addOption(new InlineHTML(), new InlineHTML());
		testObj.addOption(new InlineHTML(), new InlineHTML());
	}

	@Test
	public void shouldSelect() {
		// given
		final int assumedSelected = 1;

		// when
		testObj.setSelectedIndex(1);

		// then
		assertEquals(assumedSelected, testObj.getSelectedIndex());
	}

	@Test
	public void shouldEnable() {
		// when
		testObj.setEnabled(true);

		// then
		assertTrue(testObj.isEnabled());
	}

	@Test
	public void shouldDisable() {
		// given
		testObj.setEnabled(true);

		// when
		testObj.setEnabled(false);

		// then
		assertFalse(testObj.isEnabled());
	}
}
