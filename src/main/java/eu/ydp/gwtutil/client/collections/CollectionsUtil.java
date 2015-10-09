package eu.ydp.gwtutil.client.collections;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionsUtil {

    public static <T> Collection<T> fill(Collection<T> collection, T value, int count) {
        for (int i = 0; i < count; i++) {
            collection.add(value);
        }
        return collection;
    }

    public static <T> List<T> fillList(T value, int count) {
        List<T> collection = new ArrayList<T>();
        for (int i = 0; i < count; i++) {
            collection.add(value);
        }
        return collection;
    }

    public static <T> int indexOfNot(List<T> collection, T value) {
        for (int i = 0; i < collection.size(); i++) {
            T element = collection.get(i);
            if (value != element && ((value == null && element != null) || !value.equals(element))) {
                return i;
            }
        }
        return -1;
    }

    public static int intCollectionSum(Iterable<? extends Number> collection) {
        int sum = 0;
        for (Number value : collection) {
            sum += value.intValue();
        }
        return sum;
    }

    public static <T> boolean containsAnyOfElements(Iterable<? extends T> elements, Collection<T> collection) {
        for (T element : elements) {
            if (collection.contains(element)) {
                return true;
            }
        }
        return false;
    }

    public static JsArrayString iterableToJsArray(Iterable<String> iterable) {
        JsArrayString array = JavaScriptObject.createArray().cast();
        for (String el : iterable) {
            array.push(el);
        }
        return array;
    }

    public static List<Integer> getRangeList(int lower, int upper) {
        return ContiguousSet.create(Range.closed(lower, upper), DiscreteDomain.integers()).asList();
    }
}
