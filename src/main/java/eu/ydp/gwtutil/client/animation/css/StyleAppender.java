package eu.ydp.gwtutil.client.animation.css;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.StyleElement;
import com.google.gwt.dom.client.Text;
import com.google.gwt.user.client.DOM;

public class StyleAppender {
    private final StyleElement style;

    public StyleAppender() {
        style = StyleElement.as(DOM.createElement("style"));
        Document.get().getElementsByTagName("head").getItem(0).appendChild(style);
    }

    public void appendStyleToDocument(String template) {
        Text text = Text.as(Document.get().createTextNode(template));
        style.appendChild(text);
    }
}
