package eu.ydp.gwtutil.client.event;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.gwt.core.client.GWT;

public class EventHandlerRegistrator<H, K> {
	private final Map<K, Set<H>> handlers = new HashMap<K, Set<H>>();

	protected HandlerRegistration addHandler(final H handler, final K key) {
		final Set<H> eventHandlers = getHandlers(key);
		eventHandlers.add(handler);
		HandlerRegistration registration = new HandlerRegistration() {

			@Override
			public void removeHandler() {
				eventHandlers.remove(handler);
			}
		};
		return registration;
	}

	protected Set<H> getHandlersAccordingToRunningMode(K key) {
		Set<H> eventHandlers = getHandlers(key);

		// concurrentModificationException in dev mode
		if (GWT.isProdMode()) {
			eventHandlers = new HashSet<H>(eventHandlers);
		}
		return eventHandlers;
	}

	protected Set<H> getHandlers(K key) {
		Set<H> eventHandlers = null;
		if ((eventHandlers = handlers.get(key)) == null) { // NOPMD
			eventHandlers = new HashSet<H>();
			handlers.put(key, eventHandlers);
		}
		return eventHandlers;
	}
}
