package eu.ydp.gwtutil.client.xml;

import java.util.ArrayList;
import java.util.Collection;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ElementNodeListIterable {

	public static Iterable<Element> of(NodeList nodes){
		
		Collection<Element> elements = new ArrayList<Element>();
		
		for (int i = 0 ; i < nodes.getLength() ; i ++ ){
			if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE){
				Element element = (Element)nodes.item(i);
				elements.add(element);
			}
		}
		
		return elements;		
	}
}
