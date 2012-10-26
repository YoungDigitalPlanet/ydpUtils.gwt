package eu.ydp.gwtutil.client.xml.proxy;

import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;

public abstract class XMLProxyFactory {

	private XMLProxyFactory(){}

	@Inject static XMLProxy xmlProxy;
	
	public static XMLProxy getXMLProxy(){
		if (xmlProxy == null){
			xmlProxy = GWT.create(XMLProxy.class);
		}
		return xmlProxy;
	}
}
