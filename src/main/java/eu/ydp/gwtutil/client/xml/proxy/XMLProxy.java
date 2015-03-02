package eu.ydp.gwtutil.client.xml.proxy;

import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;

public interface XMLProxy {
	NodeList getElementsByTagName(Element element, String tagName);
}
