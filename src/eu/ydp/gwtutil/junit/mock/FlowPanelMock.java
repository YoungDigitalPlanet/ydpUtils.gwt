package eu.ydp.gwtutil.junit.mock;

import org.mockito.Mockito;

import com.google.gwt.user.client.ui.FlowPanel;

public class FlowPanelMock {
	public FlowPanel createFlowPanelMock() {
		FlowPanel panel = Mockito.mock(FlowPanel.class);
		// ElementMock em = new ElementMock();
		// com.google.gwt.user.client.Element element = em.createElementMock();
		// Mockito.when(panel.getElement()).thenReturn(element);
		return panel;
	}
}
