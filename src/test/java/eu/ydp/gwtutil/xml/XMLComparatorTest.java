package eu.ydp.gwtutil.xml;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.w3c.dom.Element;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;

public class XMLComparatorTest {

    String xmlSimple = "<a><b c='d'>e</b></a>";
    String xmlSimpleDeep = "<a><b><c d='e'>f</c></b></a>";

    @Test
    public void testCompareSame() {
        String xml = "<a><b><c d='e'/><f g='h'/></b><i><j k='m'/><n> ooo ppp <q> <r s='t'/> </q> <u w='v'/> xxx </n></i></a>";
        compareXmls(xml, xml, isEmptyString());
    }

    @Test
    public void testCompareWhitespaceNodes() {
        String xml = "<a> <b/><d/>\n<e/> </a>";
        compareXmls(xml, xml.replaceAll("\\s", ""), isEmptyString());
        compareXmls(xml.replaceAll("\\s", ""), xml, isEmptyString());
    }

    @Test
    public void testCompareModifiedElementName() {
        String xml2 = "<a><bb c='d'>e</bb></a>";
        compareXmls(xmlSimple, xml2, not(isEmptyString()));
    }

    @Test
    public void testCompareModifiedAttributeName() {
        String xml2 = "<a><b cc='d'>e</b></a>";
        compareXmls(xmlSimple, xml2, not(isEmptyString()));
    }

    @Test
    public void testCompareModifiedAttributeValue() {
        String xml2 = "<a><b c='dd'>e</b></a>";
        compareXmls(xmlSimple, xml2, not(isEmptyString()));
    }

    @Test
    public void testCompareModifiedTextNodeValue() {
        String xml2 = "<a><b c='d'>ee</b></a>";
        compareXmls(xmlSimple, xml2, not(isEmptyString()));
    }

    @Test
    public void testCompareNodesCount() {
        String xml1 = "<a><b/><b/></a>";
        String xml2 = "<a><b/></a>";
        compareXmls(xml1, xml2, not(isEmptyString()));
    }

    @Test
    public void testCompareMultipleNodesCount() {
        String xml1 = "<a><b/><b/><c/></a>";
        String xml2 = "<a><b/><c/><c/></a>";
        compareXmls(xml1, xml2, not(isEmptyString()));
    }

    @Test
    public void testCompareDeepModifiedNodeName() {
        String xml2 = "<a><b><cc d='e'>f</cc></b></a>";
        compareXmls(xmlSimpleDeep, xml2, not(isEmptyString()));
    }

    @Test
    public void testCompareDeepModifiedAttributeName() {
        String xml2 = "<a><b><c dd='e'>f</c></b></a>";
        compareXmls(xmlSimpleDeep, xml2, not(isEmptyString()));
    }

    @Test
    public void testCompareDeepModifiedAttributeValue() {
        String xml2 = "<a><b><c d='ee'>f</c></b></a>";
        compareXmls(xmlSimpleDeep, xml2, not(isEmptyString()));
    }

    @Test
    public void testCompareDeepModifiedTextNodeValue() {
        String xml2 = "<a><b><c d='e'>ff</c></b></a>";
        compareXmls(xmlSimpleDeep, xml2, not(isEmptyString()));
    }

    @Test
    public void testCompareOrderNodeName() {
        String xml1 = "<a><b c='d'>e<f/></b><b c='d'>e<g/></b></a>";
        String xml2 = "<a><b c='d'>e<g/></b><b c='d'>e<f/></b></a>";
        compareXmls(xml1, xml2, isEmptyString());
    }

    @Test
    public void testCompareOrderAttributeName() {
        String xml1 = "<a><b c='d'>e</b><b c='f'>e</b></a>";
        String xml2 = "<a><b c='f'>e</b><b c='d'>e</b></a>";
        compareXmls(xml1, xml2, isEmptyString());
    }

    @Test
    public void testCompareOrderAttributeValue() {
        String xml1 = "<a><b c='d'>e</b><b c='f'>e</b></a>";
        String xml2 = "<a><b c='f'>e</b><b c='d'>e</b></a>";
        compareXmls(xml1, xml2, isEmptyString());
    }

    @Test
    public void testCompareOrderTextNodeName() {
        String xml1 = "<a><b c='d'>e</b><b c='d'>f</b></a>";
        String xml2 = "<a><b c='d'>f</b><b c='d'>e</b></a>";
        compareXmls(xml1, xml2, isEmptyString());
    }

    private void compareXmls(String xml1, String xml2, Matcher<String> matcher) {
        Element el1 = XMLParser.parseW3c(xml1).getDocumentElement();
        Element el2 = XMLParser.parseW3c(xml2).getDocumentElement();
        String result = XMLComparator.compare(el1, el2);
        assertThat(result, matcher);
    }
}
