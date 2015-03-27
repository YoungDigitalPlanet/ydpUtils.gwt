package eu.ydp.gwtutil.client.collections;

import java.util.ArrayList;
import java.util.List;

public final class ListCreator<T> {

	private List<T> list;

	private ListCreator() {
		list = new ArrayList<T>();
	}

	private ListCreator(List<T> list) {
		this.list = list;
	}

	public static <T> ListCreator<T> create(T element) {
		ListCreator<T> listCreator = new ListCreator<T>();
		listCreator.list.add(element);
		return listCreator;
	}

	public static <T> ListCreator<T> create(List<T> list) {
		assert list != null;
		ListCreator<T> listCreator = new ListCreator<T>(list);
		return listCreator;
	}

	public ListCreator<T> add(T element) {
		list.add(element);
		return this;
	}

	public List<T> build() {
		return list;
	}
}
