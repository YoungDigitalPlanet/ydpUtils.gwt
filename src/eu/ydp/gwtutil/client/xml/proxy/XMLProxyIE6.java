package eu.ydp.gwtutil.client.xml.proxy;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.impl.DomXmlAccessor;

public class XMLProxyIE6 implements XMLProxy {
	
	XMLProxyIE6(){}

	public NodeList getElementsByTagName(Element element, String tagName) {
		DomXmlAccessor acc = new DomXmlAccessor();
		return acc.createNodeListImpl(getElementsByTagNameImpl(acc.getJavaScriptObject(element), tagName));
	}
	
	private native JavaScriptObject getElementsByTagNameImpl(JavaScriptObject o, String tagName) /*-{
		return o.getElementsByTagName(tagName);
	}-*/;
	
}
