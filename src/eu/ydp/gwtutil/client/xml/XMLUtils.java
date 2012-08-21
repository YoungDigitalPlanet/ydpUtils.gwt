package eu.ydp.gwtutil.client.xml;

import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

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
	 *            rodzic z którego pobieramy teksty
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
		NodeList nodeList = element.getElementsByTagName(tagName);

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
		NodeList children = element.getElementsByTagName(nodeName);		
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

}
