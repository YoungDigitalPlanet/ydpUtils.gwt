package eu.ydp.gwtutil.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.Widget;

public class InputToButtonReplacer {

	public void changeInputTypeChildToButton(Focusable focusable) {
		Element element = ((Widget) focusable).getElement();
		if (element.getChildCount() > 0) {
			Element elementInput = (Element) element.getChild(0);
			elementInput.setAttribute("type", "button");
		}
	}
}
