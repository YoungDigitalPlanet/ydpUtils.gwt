package eu.ydp.gwtutil.client.dom;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

public class DOMTreeWalker {
    protected final JavaScriptObject walker = createTreeWalker();

    public native void nextNode(Element element)/*-{
        try {
            var walker = this.@eu.ydp.gwtutil.client.dom.DOMTreeWalker::walker;
            if (walker == null) {
                return;
            }

            walker.currentNode = element;
            if (!walker.nextNode()) {
                // Restart search from the start of the document
                walker.currentNode = walker.root;
                walker.nextNode();
            }
            if (walker.currentNode && walker.currentNode != walker.root) {
                walker.currentNode.focus();
            }
        } catch (e) {
            console.log(e);
        }
    }-*/;

    private native JavaScriptObject createTreeWalker() /*-{
        try {
            $doc.allowedTags = {
                input: true,
                textarea: true,
                button: true
            };
            var walker = $doc.createTreeWalker($doc.body,
                NodeFilter.SHOW_ALL, {
                    acceptNode: function (node) {
                        console.log(node);
                        console.log(node.localName);
                        console.log($doc.allowedTags);
                        if (node.localName in $doc.allowedTags)
                            return NodeFilter.FILTER_ACCEPT;
                        else
                            NodeFilter.FILTER_SKIP;
                    }
                }, false);
            return walker;
        } catch (e) {
            console.log(e);
            return null;
        }
    }-*/;
}
