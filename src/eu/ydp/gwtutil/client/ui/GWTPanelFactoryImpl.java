package eu.ydp.gwtutil.client.ui;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FlowPanel;

public class GWTPanelFactoryImpl implements GWTPanelFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.ydp.gwtutil.client.ui.GWTPanelFactory#getFlowPanel()
	 */
	@Override
	public FlowPanel getFlowPanel() {
		return new FlowPanel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.ydp.gwtutil.client.ui.GWTPanelFactory#getAbsolutePanel()
	 */
	@Override
	public AbsolutePanel getAbsolutePanel() {
		return new AbsolutePanel();
	}

}
