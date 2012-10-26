package com.google.gwt.xml.client.impl;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.xml.client.Element;

public class DomXmlAccessor {


	public JavaScriptObject getJavaScriptObject(Element element){
		return getJavaScriptObjectImpl((DOMItem)element);
	}
	
	private JavaScriptObject getJavaScriptObjectImpl(DOMItem item){
		return item.getJsObject();
	}
	
	public NodeListImpl createNodeListImpl(JavaScriptObject o){
		return new NodeListImpl(o);
	}
}
