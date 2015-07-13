package eu.ydp.gwtutil.xml;

import com.google.gwt.xml.client.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;

public final class XMLParser {

    private XMLParser() {
    }

    public static Document parse(String contents) {
        return new DocumentWrapper(parseW3c(contents));
    }

    public static Document createDocument() {
        try {
            return new DocumentWrapper(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static org.w3c.dom.Document parseW3c(String contents) {
        try {
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(contents));
            return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String nodeToString(Node node) {
        StringWriter sw = new StringWriter();
        try {
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(node), new StreamResult(sw));
        } catch (TransformerException te) {
            System.out.println("nodeToString Transformer Exception"); // NOPMD
        }
        return sw.toString();
    }

}
