package eu.ydp.gwtutil.client.xml;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.xml.client.Attr;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NamedNodeMap;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

import eu.ydp.gwtutil.client.StringUtils;
import eu.ydp.gwtutil.client.xml.proxy.XMLProxy;
import eu.ydp.gwtutil.client.xml.proxy.XMLProxyFactory;

public final class XMLUtils {

	private XMLUtils() {
	}

	/**
	 * Helper function for getting element attribute as string
	 *
	 * @param name
	 *            Attribute name
	 * @return attribute text or empty string if not found
	 */
	public static String getAttributeAsString(Element element, String name) {
		return getAttributeAsString(element, name, "");
	}

	/**
	 * Helper function for getting element attribute as string
	 *
	 * @param name
	 *            Attribute name
	 * @param defaultValue wartosc domyslna zwracana jezeli atrybut nie istnieje
	 * @return attribute text or empty string if not found
	 */
	public static String getAttributeAsString(Element element, String name, String defaultValue) {
		String attribute = element.getAttribute(name);
		return attribute == null ? defaultValue : attribute;
	}

	/**
	 * Helper function for getting element attribute as boolean
	 *
	 * @param name
	 *            Attribute name
	 * @return attribute value or false if not found
	 */
	public static boolean getAttributeAsBoolean(Element element, String name) {
		String attribute = element.getAttribute(name);
		return "true".equals(attribute);
	}

	/**
	 * Helper function for getting element attribute as int
	 *
	 * @param name
	 *            Attribute name
	 * @return attribute value or 0 if not found
	 */
	public static int getAttributeAsInt(Element element, String name) {
		String attribute = element.getAttribute(name);
		return attribute == null ? 0 : Integer.parseInt(attribute);
	}

	/**
	 * Helper function for getting element attribute as double
	 *
	 * @param name
	 *            Attribute name
	 * @return attribute value or 0 if not found
	 */
	public static double getAttributeAsDouble(Element element, String name) {
		String attribute = element.getAttribute(name);
		return attribute == null ? 0 : Double.parseDouble(attribute);
	}

	/**
	 * get all TEXT nodes
	 *
	 * @return contents of tag
	 */
	public static String getText(Element element) {
		return getText(element, false);
	}

	/**
	 * zwraca tekst z elementow {@link Node.TEXT_NODE} wszystkich podelementow
	 * elementu
	 *
	 * @param element
	 *            przeszukiwany obiekt
	 * @return
	 */
	public static String getTextFromChilds(Element element) {
		return getText(element, true);
	}

	/**
	 * Zwraca tekst z dzieci typu {@link Node.TEXT_NODE} dla element
	 *
	 * @param element
	 *            rodzic z ktorego pobieramy teksty
	 * @param allChilds
	 *            czy przechodzimy rekurencyjnie przez wszystkie dzieci
	 * @return
	 */
	private static String getText(Element element, boolean allChilds) {
		StringBuilder text = new StringBuilder();
		if (element != null) {
			NodeList nodes = element.getChildNodes();
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if (node.getNodeType() == Node.TEXT_NODE) {
					text.append(node.getNodeValue());
					text.append(' ');
				} else if (allChilds && node.getNodeType() == Node.ELEMENT_NODE) {
					text.append(getText((Element) node, allChilds));
					text.append(' ');
				}
			}
		}
		return text.toString().trim();
	}

	/**
	 * Get first element with given tag name
	 *
	 * @param tagName
	 * @return first element or null if not found
	 */
	public static Element getFirstElementWithTagName(Element element, String tagName) {

		Element node = null;	
		XMLProxy xmlProxy = XMLProxyFactory.getXMLProxy();
		NodeList nodeList = xmlProxy.getElementsByTagName(element, tagName);

		if (nodeList.getLength() > 0) {
			node = (Element) nodeList.item(0);
		}

		return node;

	}

	public static Element getFirstChildElement(Element element) {
		for (int i = 0; i < element.getChildNodes().getLength(); i++) {
			if (element.getChildNodes().item(i) instanceof Element) {
				return (Element) element.getChildNodes().item(i);// NOPMD
			}
		}
		return null;
	}

	@SuppressWarnings("PMD")
	public static boolean hasParentWithNodeName(Element element, String parentNodeName, String searchUpToNodeName) {
		if (element != null && element.getNodeName().equals(parentNodeName)) {
			return true;
		}
		if (element == null || element.getNodeName().equals(searchUpToNodeName) || !(element.getParentNode() instanceof Element)) {
			return false;
		}
		return hasParentWithNodeName((Element) element.getParentNode(), parentNodeName, searchUpToNodeName);
	}
	
	public static Element getFirstChildElementByNodeName(Element element, String nodeName){
		for (int i = 0 ; i < element.getChildNodes().getLength() ; i ++){
			if (element.getChildNodes().item(i) instanceof Element  &&  element.getChildNodes().item(i).getNodeName().equals(nodeName)){
				return (Element)element.getChildNodes().item(i);
			}
		}
		return null;
	}
	
	public static NodeList getElementsByAttribute(Element element, String nodeName, String attrName){		
		XMLProxy xmlProxy = XMLProxyFactory.getXMLProxy();
		NodeList children = xmlProxy.getElementsByTagName(element, nodeName);
		if ("*".equals(nodeName)  &&  children.getLength() == 0){
			children = element.getElementsByTagName("div");
		}
		Element child;
		YNodeListImpl result = new YNodeListImpl();
		int i;
		
		for(i = 0;i < children.getLength(); i++){
			if(children.item(i) instanceof Element){
				child = (Element)children.item(i);				
				if(child.hasAttribute(attrName)){
					result.add(child);
				}
			}
		}
		return result;
		
		
	}
	
	public static NodeList getElementsByAttribute(Element element, String nodeName, String attrName, String attrValue){
		NodeList elems = getElementsByAttribute(element, nodeName, attrName);
		YNodeListImpl result = new YNodeListImpl();
		int i;
		Element e;
		for(i = 0;i < elems.getLength(); i++){
			e = (Element)elems.item(i);
			if(e.getAttribute(attrName).equals(attrValue))
				result.add(e);
		}
		
		return result;
	}
	
	/**
	 * Strips element nodes and returns the contents (node value) of the element.
	 * 
	 * <p>Ex. for </p>
	 * <p>{@code<a x="y"><b c="d">e</b>f</a>}</p> 
	 * <p>the result will be</p>
	 * <p>{@code"<b c="d">e</b>f"}</p>
	 * 
	 * @param element
	 * @return Contents of the element node as <code>String</code> or empty string if the element has no contents. 
	 */
	public static String getElementInnerContentAsString(Element element){
		String elementString = element.toString();
		if (elementString.indexOf(">") < elementString.lastIndexOf("</")){
			return elementString.substring(elementString.indexOf(">")+1, elementString.lastIndexOf("</"));
		}
		return StringUtils.EMPTY_STRING;
	}

	/**
	 * Converts attributes of the given element to map.
	 * 
	 * @param element Element to map
	 * @param ignore array of attributes that should be ignored (not added to the map if they appear)
	 * @return map of pairs attribute name -> attribute value
	 */
	public static Map<String, String> attributesToMap(Element element, String... ignore){
		
		NamedNodeMap attrs = element.getAttributes();				
		Map<String, String> mapParams = new HashMap<String, String>();
		List<String> ignoreList = Arrays.asList(ignore);
		
		for(int i = 0; i < attrs.getLength() ; i++){
			Attr attribute = (Attr)attrs.item(i);
			String attrName = attribute.getName();
			if(!ignoreList.contains(attrName)){
				String attrValue = attribute.getValue();
				mapParams.put(attrName, attrValue);
			}
		}
		
		return mapParams;		
		
	}
	
	public static NodeList getChildrenByTagName(Element node, String name) {
				
		YNodeListImpl nodeList = new YNodeListImpl();
		NodeList children = node.getChildNodes();
		Node child;
	    for(int i = 0; i < children.getLength(); i++ ){
	    	child = children.item(i);
	    	if(child.getNodeType() == Node.ELEMENT_NODE && name.equals(child.getNodeName())) {
	    		nodeList.add((Element)child);
	    	}
	    }		
		
	    return nodeList;
	  }
}
