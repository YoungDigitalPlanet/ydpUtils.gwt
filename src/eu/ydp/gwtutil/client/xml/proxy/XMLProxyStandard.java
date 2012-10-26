package eu.ydp.gwtutil.client.xml.proxy;

import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;

public class XMLProxyStandard implements XMLProxy {
	
	XMLProxyStandard(){}

	@Override
	public NodeList getElementsByTagName(Element element, String tagName) {
		return element.getElementsByTagName(tagName);
	}

}
