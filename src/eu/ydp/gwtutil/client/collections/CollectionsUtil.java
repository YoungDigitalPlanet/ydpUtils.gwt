package eu.ydp.gwtutil.client.collections;

import java.util.Collection;

public class CollectionsUtil {

	public static <T> Collection<T> fill(Collection<T> collection, T value, int count){
		for (int i = 0 ; i < count ; i ++ )
			collection.add(value);
		return collection;
	}
}
