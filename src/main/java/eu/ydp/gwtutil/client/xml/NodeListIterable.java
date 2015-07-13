package eu.ydp.gwtutil.client.xml;

import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

import java.util.Iterator;

public class NodeListIterable implements Iterable<Node> {

    private NodeList nodes;

    public NodeListIterable(NodeList nodes) {
        this.nodes = nodes;
    }

    @Override
    public Iterator<Node> iterator() {
        return new NodeListIterator(nodes);
    }

    private static class NodeListIterator implements Iterator<Node> {

        private NodeList nodes;
        private int counter;

        public NodeListIterator(NodeList nodes) {
            this.nodes = nodes;
            this.counter = 0;
        }

        @Override
        public boolean hasNext() {
            return counter < nodes.getLength();
        }

        @Override
        public Node next() {
            return nodes.item(counter++);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
