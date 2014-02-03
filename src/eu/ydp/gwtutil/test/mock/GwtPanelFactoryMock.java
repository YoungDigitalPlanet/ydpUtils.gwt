package eu.ydp.gwtutil.test.mock;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FlowPanel;

import eu.ydp.gwtutil.client.ui.GWTPanelFactory;
import eu.ydp.gwtutil.junit.mock.FlowPanelMock;

public class GwtPanelFactoryMock implements GWTPanelFactory {

	@Override
	public FlowPanel getFlowPanel() {
		FlowPanelMock fpm = new FlowPanelMock();
		return fpm.createFlowPanelMock();
	}

	@Override
	public AbsolutePanel getAbsolutePanel() {

		return null;
	}

}
