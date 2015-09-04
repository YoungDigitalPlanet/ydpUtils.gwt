package eu.ydp.gwtutil.client.xml;

import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;
import eu.ydp.gwtutil.client.xml.proxy.XMLProxyFactory;
import eu.ydp.gwtutil.xml.ElementWrapper;
import eu.ydp.gwtutil.xml.XMLComparator;
import eu.ydp.gwtutil.xml.XMLParser;
import eu.ydp.gwtutil.xml.XMLProxyWrapper;
import org.junit.Assert;
import org.junit.Test;

public class XmlUtilTest {

    @Test
    public void getElementsByAttribute() {
        XMLProxyFactory.xmlProxy = new XMLProxyWrapper();

        Element element = createXML();

        NodeList elems = XMLUtils.getElementsByAttribute(element, "div", "type", "config");
        Assert.assertEquals(1, elems.getLength());

        elems = XMLUtils.getElementsByAttribute(element, "div", "type", "content");
        Assert.assertEquals(1, elems.getLength());

        elems = XMLUtils.getElementsByAttribute(element, "property", "name", "twoState");
        Assert.assertEquals(1, elems.getLength());

        elems = XMLUtils.getElementsByAttribute(element, "aaa", "bbbb", "cccc");
        Assert.assertEquals(0, elems.getLength());

        elems = XMLUtils.getElementsByAttribute(element, "aaa", "bbbb");
        Assert.assertEquals(0, elems.getLength());

        elems = XMLUtils.getElementsByAttribute(element, "*", "target");
        Assert.assertEquals(4, elems.getLength());
        int i;
        for (i = 0; i < elems.getLength(); i++) {
            Assert.assertEquals("tab_view_content", ((Element) elems.item(i)).getAttribute("target"));
        }

    }

    @Test
    public void testGetFirstChildElementByNodeName() {
        String bString = "<b><c d='e'/></b>";
        String bString2 = "<b><c d='f'/></b>";
        Element bElement = XMLParser.parse(bString).getDocumentElement();
        Element element = XMLParser.parse("<a><x/><y>" + bString2 + "</y>" + bString + "<f></f><g/><h>" + bString2 + "</h></a>").getDocumentElement();
        Element output = XMLUtils.getFirstChildElementByNodeName(element, "b");
        Assert.assertEquals("", XMLComparator.compare(((ElementWrapper) bElement).getElement(), ((ElementWrapper) output).getElement()));
    }

    public Element createXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<div id='tab_info' module='link'>");
        sb.append("		<div style='display:none' type='config'>");
        sb.append("			<places>");
        sb.append("				<place id='info_view1' target='tab_view_content'/>");
        sb.append("				<place id='info_view2' target='tab_view_content'/>");
        sb.append("				<place id='info_view3' target='tab_view_content'/>");
        sb.append("				<place id='info_view4' target='tab_view_content'/>");
        sb.append("			</places>");
        sb.append("			<properties>");
        sb.append("				<property name='twoState' value='true'/>");
        sb.append("				<property name='anyProperty' value='true'/>");
        sb.append("			</properties>");
        sb.append("		</div>");
        sb.append("		<div type='content'>");
        sb.append("			<div>content</div>");
        sb.append("		</div>");
        sb.append("</div>");

        Element el = XMLParser.parse(sb.toString()).getDocumentElement();

        return el;

    }
}
