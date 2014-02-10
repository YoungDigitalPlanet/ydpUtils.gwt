package eu.ydp.gwtutil.client.collections;

import java.util.ArrayList;

public class Stack<E> extends ArrayList<E> {
	private static final long serialVersionUID = -9222980209009432057L;

	public void push(E v) {
		add(v);
	}

	public E pop() {
		return remove(size() - 1);
	}

	public E peek() {
		return get(size() - 1);
	}
}
