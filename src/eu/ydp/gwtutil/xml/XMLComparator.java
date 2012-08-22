package eu.ydp.gwtutil.xml;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import eu.ydp.gwtutil.client.collections.CollectionsUtil;


public final class XMLComparator {
	
	private XMLComparator(){}
	
	public static String compare(Node ref, Node test){

		String result = "";
		
		// compare type
		if (ref.getNodeType() != test.getNodeType()){
			return "node type does not match";
		}
		
		if (ref.getNodeType() == Node.TEXT_NODE){
			return compareTextNodes(ref, test);
		}
		
		String refName = ref.getNodeName();
		if (refName != null  &&  !refName.equals(test.getNodeName())){
			return refName + " node name not matched";
		}
		
		// compare attributes
		
		result = compareAttributes(ref, test);
		if (!"".equals(result))
			return result;
		
		// compare child nodes
		
		NodeList refChildNodes = ref.getChildNodes();
		NodeList testChildNodes = test.getChildNodes();		
		
		result = compareNodesCount(refChildNodes, testChildNodes, Node.ELEMENT_NODE);
		if (!"".equals(result))
			return refName + " child elements count not match: " + result;	
		
		result = compareNodesCount(refChildNodes, testChildNodes, Node.TEXT_NODE);
		if (!"".equals(result))
			return refName + " child nonempty text nodes count not match: " + result;		

		List<Boolean> testNodesUsed = (List<Boolean>)CollectionsUtil.fill(new ArrayList<Boolean>(), false, testChildNodes.getLength());
		
		for (int r = 0 ; r < refChildNodes.getLength() ; r ++){
			
			int t = 0;
			boolean found = false;
			
			if (isEmptyTextNode(refChildNodes.item(r)))
				continue;
			
			for (t = 0 ; t < testChildNodes.getLength() ; t ++){
				
				if (!testNodesUsed.get(t)){
				
					result = compare(refChildNodes.item(r), testChildNodes.item(t));
					
					if ("".equals(result)){
						found = true;
						break;
					}
				}
				
			}
			if (found){
				testNodesUsed.set(t, true);
			} else {
				return "matching " + nodeToString(refChildNodes.item(r)) + " node not found";
			}
		}
		
		return "";
	}
	
	private static boolean isEmptyTextNode(Node item) {
		if (item.getNodeType() == Node.TEXT_NODE  &&  item.getNodeValue().trim().length() == 0)
			return true;
		return false;
	}

	private static String nodeToString(Node node){
		if (node.getNodeName() != null){
			return node.getNodeName();
		} else if (node.getNodeValue() != null){
			return node.getNodeValue();
		}
		return "";
	}

	private static String compareTextNodes(Node ref, Node test) {
		if ((ref.getNodeValue().trim().length() == 0  || test.getNodeValue().trim().length() == 0)  &&  (ref.getNodeValue().trim().length() != test.getNodeValue().trim().length()))
			return "comparing empty to non-empty text node";
		if (ref.getNodeValue().trim().length() == 0  && test.getNodeValue().trim().length() == 0)
			return "";
		if (ref.getNodeValue().equals(test.getNodeValue()))
			return "";
		return ref.getNodeValue() + " does not match " + test.getNodeValue();
	}

	private static String compareNodesCount(NodeList refChildNodes, NodeList testChildNodes, short type) { // NOPMD
		int refCount = getNodesCount(refChildNodes, type);
		int testCount = getNodesCount(testChildNodes, type);
		if (refCount != testCount)
			return refCount + " to " + testCount;
		return "";
	}
	
	private static int getNodesCount(NodeList nodeList, short type){ // NOPMD
		int count = 0;
		for (int i = 0 ; i < nodeList.getLength() ; i ++){
			if (nodeList.item(i).getNodeType() == type  &&  (type != Node.TEXT_NODE  ||  nodeList.item(i).getNodeValue() == null  ||  nodeList.item(i).getNodeValue().trim().length() > 0)){
				count++;
			}
		}
		return count;
	}

	private static String compareAttributes(Node ref, Node test){
		
		String result = "";
		
		for (int r = 0 ; r < ref.getAttributes().getLength() ; r ++){
			String name = ref.getAttributes().item(r).getNodeName();
			String value = ref.getAttributes().item(r).getNodeValue();
			
			result = ref.getNodeName() + " - " + name; 
			
			for (int t = 0 ; t < test.getAttributes().getLength() ; t ++){
				if (test.getAttributes().item(t).getNodeName().equals(name)){
					if (test.getAttributes().item(t).getNodeValue().equals(value)){
						result = "";
						break;
					}
				}
			}
			
			if (result.length() >  0)
				return result;
		}
		return result;
	}


}
