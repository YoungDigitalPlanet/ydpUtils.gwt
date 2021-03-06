package eu.ydp.gwtutil.client.collections;

import java.util.*;

public class QueueSet<E> implements Set<E> {

    private List<E> list = new ArrayList<E>();

    @Override
    public boolean add(E e) {
        if (list.contains(e))
            return false;
        list.add(e);
        return true;
    }

    public boolean append(E e) {
        if (list.contains(e)) {
            list.remove(e);
        }
        list.add(e);
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean added = false;
        for (E e : c) {
            if (!list.contains(e)) {
                list.add(e);
                added = true;
            }
        }
        return added;
    }

    public boolean appendAll(Collection<? extends E> c) {
        boolean added = false;
        if (c != null) {
            for (E e : c) {
                if (!list.contains(e)) {
                    list.add(e);
                    added = true;
                } else if (list.indexOf(e) != list.size() - 1) {
                    list.remove(e);
                    list.add(e);
                    added = true;
                }
            }
        }
        return added;
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return list.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return list.retainAll(c);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return toArray(a);
    }

}
