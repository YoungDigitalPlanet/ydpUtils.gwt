package eu.ydp.gwtutil.client.ui;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FlowPanel;

public interface GWTPanelFactory {

    public abstract FlowPanel getFlowPanel();

    public abstract AbsolutePanel getAbsolutePanel();

}
