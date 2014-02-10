package eu.ydp.gwtutil.client.collections;

import java.util.Iterator;
import java.util.List;

/**
 * Iterate List in reverse collection
 * 
 * @param <T>
 *            List type
 */
public class ReverseIterator<T> implements Iterator<T> {

	private List<T> elements;
	private int currentIx;

	/**
	 * Iterate over passed list in reverse direction
	 * 
	 * @param lst
	 *            list to iterate over
	 */
	public ReverseIterator(List<T> lst) {
		if (lst == null)
			throw new IllegalArgumentException("Can't iterate null List");

		this.elements = lst;
		currentIx = this.elements.size() - 1;
	}

	@Override
	public boolean hasNext() {
		return currentIx >= 0;
	}

	@Override
	public T next() {
		return elements.get(currentIx--);
	}

	@Override
	public void remove() {
		elements.remove(currentIx + 1);
	}

}
