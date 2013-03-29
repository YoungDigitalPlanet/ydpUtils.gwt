package eu.ydp.gwtutil.client.event;

import java.util.HashSet;
import java.util.Set;

import com.google.gwt.core.client.GWT;

import eu.ydp.gwtutil.client.event.EventImpl.Type;

public abstract class AbstractEventHandler<H extends EventHandler, E extends Enum<E>, EV extends Event<H, E>> extends EventHandlerRegistrator<H, Type<H, E>> {
	protected HandlerRegistration[] addHandlers(final H handler, final Type<H, E>[] event) {
		HandlerRegistration[] registrations = new HandlerRegistration[event.length];
		for (int x = 0; x < event.length; ++x) {
			registrations[x] = addHandler(handler, event[x]);
		}
		return registrations;
	}

	protected void fireEvent(EV event) {
		// concurrentModificationException in dev mode
		final Set<H> eventHandlers = GWT.isProdMode() ? getHandlers(event.getAssociatedType()) : new HashSet<H>(getHandlers(event.getAssociatedType()));
		for (H handler : eventHandlers) {
			dispatchEvent(handler, event);
		}
	}

	protected abstract void dispatchEvent(H handler, EV event);

}
