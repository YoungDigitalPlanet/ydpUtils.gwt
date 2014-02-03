package eu.ydp.gwtutil.xml;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;

import com.google.gwt.xml.client.Node;

public class NodeWrapperFactory {

	public static Node createNode(org.w3c.dom.Node node) {
		if (node instanceof Element) {
			return new ElementWrapper((Element) node);
		} else if (node instanceof Attr) {
			return new AttrWrapper((Attr) node);
		}
		return new NodeWrapper(node);
	}
}
