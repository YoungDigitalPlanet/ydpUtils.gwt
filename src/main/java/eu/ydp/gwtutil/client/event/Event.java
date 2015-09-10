package eu.ydp.gwtutil.client.event;

public interface Event<H, T extends Enum<T>> {
    EventType<H, T> getAssociatedType();

    Object getSource();

    void dispatch(H handler);
}
