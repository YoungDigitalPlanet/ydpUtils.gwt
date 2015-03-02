package eu.ydp.gwtutil.client.collections;

import java.util.Iterator;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class JsArrayIterable<T extends JavaScriptObject> implements Iterable<T> {

	public static <T extends JavaScriptObject> JsArrayIterable<T> create(JsArray<T> arr) {
		return new JsArrayIterable<T>(arr);
	}

	private final JsArray<T> arr;

	private JsArrayIterable(JsArray<T> arr) {
		this.arr = arr;
	}

	@Override
	public Iterator<T> iterator() {
		return new JsArrayIterator<T>(arr);
	}

	private static class JsArrayIterator<T extends JavaScriptObject> implements Iterator<T> {

		private final JsArray<T> arr;
		private int currentIndex;

		private JsArrayIterator(JsArray<T> arr) {
			this.arr = arr;
		}

		@Override
		public boolean hasNext() {
			return arr.length() > currentIndex;
		}

		@Override
		public T next() {
			return arr.get(currentIndex++);
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("Remove not supported in JsArrayIterator");
		}

	}

}
