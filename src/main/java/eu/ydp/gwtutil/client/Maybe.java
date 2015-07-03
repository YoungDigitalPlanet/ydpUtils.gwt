package eu.ydp.gwtutil.client;

public class Maybe<T> {
    private T value;
    private boolean assigned;

    public boolean isAssigned() {
        return assigned;
    }

    public boolean isUnassigned() {
        return !assigned;
    }

    public void set(T val) {
        value = val;
        assigned = true;
    }

    public void unset() {
        value = null;
        assigned = false;
    }

    public T get() {
        return value;
    }
}
