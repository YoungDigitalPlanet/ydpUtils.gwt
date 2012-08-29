package eu.ydp.gwtutil.client.collections;

import java.util.Collection;
import java.util.List;

public class CollectionsUtil {

	public static <T> Collection<T> fill(Collection<T> collection, T value, int count){
		for (int i = 0 ; i < count ; i ++ )
			collection.add(value);
		return collection;
	}
	
	public static <T> int indexOfNot(List<T> collection, T value){
		for (int i = 0 ; i < collection.size() ; i ++ ){
			T element = collection.get(i);
			if (value != element  &&  
				((value == null  &&  element != null)  ||  !value.equals(element))){
				return i;
			}
		}
		return -1;
	}
}
