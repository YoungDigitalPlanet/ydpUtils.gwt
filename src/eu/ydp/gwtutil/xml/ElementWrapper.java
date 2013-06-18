package eu.ydp.gwtutil.xml;

import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.google.gwt.xml.client.Attr;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;

public class ElementWrapper extends NodeWrapper implements Element {

	public ElementWrapper(org.w3c.dom.Element element){
		super(element);
		this.node = element;
	}
	
	public org.w3c.dom.Element getElement(){
		return (org.w3c.dom.Element)node;
	}
	
	public static org.w3c.dom.Element getElement(Element element){
		org.w3c.dom.Element output = null;
		if (element instanceof ElementWrapper){
			output = ((ElementWrapper)element).getElement();
		}
		return output;
	}

	@Override
	public String getAttribute(String name) {
		return getElement().getAttribute(name);
	}

	@Override
	public Attr getAttributeNode(String name) {
		return new AttrWrapper(getElement().getAttributeNode(name));
	}

	@Override
	public NodeList getElementsByTagName(String name) {
		return new NodeListWrapper(getElement().getElementsByTagName(name));
	}

	@Override
	public String getTagName() {
		return getElement().getTagName();
	}

	@Override
	public boolean hasAttribute(String name) {
		return getElement().hasAttribute(name);
	}

	@Override
	public void removeAttribute(String name) {
		getElement().removeAttribute(name);
	}

	@Override
	public void setAttribute(String name, String value) {
		getElement().setAttribute(name, value);	
	}
	
	@Override
	public String toString(){
		return XMLParser.nodeToString(node);
	}
}
