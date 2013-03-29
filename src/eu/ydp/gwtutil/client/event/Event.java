package eu.ydp.gwtutil.client.event;

import eu.ydp.gwtutil.client.event.EventImpl.Type;

public interface Event<H, T extends Enum<T>> {

	/**
	 * Returns the {@link Type} used to register this event, allowing an
	 * {@link PlayerEventsBus} to find handlers of the appropriate class.
	 *
	 * @return the type
	 */
	public abstract Type<H, T> getAssociatedType();

	/**
	 * Returns the source for this event. The type and meaning of the source is
	 * arbitrary, and is most useful as a secondary key for handler
	 * registration. (See {@link PlayerEventsBus#addHandlerToSource}, which allows a
	 * handler to register for events of a particular type, tied to a particular
	 * source.)
	 * <p>
	 * Note that the source is actually set at dispatch time, e.g. via
	 * {@link PlayerEventsBus#fireEventFromSource(EventImpl, Object)}.
	 *
	 * @return object representing the source of this event
	 */
	public abstract Object getSource();

	/**
	 * This is a method used primarily for debugging. It gives a string
	 * representation of the event details. This does not override the toString
	 * method because the compiler cannot always optimize toString out
	 * correctly. Event types should override as desired.
	 *
	 * @return a string representing the event's specifics.
	 */
	public abstract String toDebugString();

	/**
	 * Implemented by subclasses to to invoke their handlers in a type safe
	 * manner. Intended to be called by {@link PlayerEventsBus#fireEvent(EventImpl)} or
	 * {@link PlayerEventsBus#fireEventFromSource(EventImpl, Object)}.
	 *
	 * @param handler
	 *            handler
	 * @see PlayerEventsBus#dispatchEvent(EventImpl, Object)
	 */
	public abstract void dispatch(H handler);

}