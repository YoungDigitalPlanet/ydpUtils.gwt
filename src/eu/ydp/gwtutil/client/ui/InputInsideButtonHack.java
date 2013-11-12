package eu.ydp.gwtutil.client.ui;

import com.google.gwt.user.client.ui.Focusable;
import com.google.inject.Inject;

import eu.ydp.gwtutil.client.util.UserAgentChecker;
import eu.ydp.gwtutil.client.util.UserAgentChecker.UserAgent;

public class InputInsideButtonHack {

	@Inject
	private InputToButtonReplacer inputReplacer;

	public void activateIfNeededFor(Focusable button) {
		if (!UserAgentChecker.isUserAgent(UserAgent.IE10)) {
			inputReplacer.changeInputTypeChildToButton(button);
		}
	}
}
