package eu.ydp.gwtutil.xml;

import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;
import eu.ydp.gwtutil.client.xml.proxy.XMLProxy;

public class XMLProxyWrapper implements XMLProxy {

    @Override
    public NodeList getElementsByTagName(Element element, String tagName) {
        return element.getElementsByTagName(tagName);
    }

}
