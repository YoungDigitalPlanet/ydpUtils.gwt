package eu.ydp.gwtutil.client.event;

public interface HandlerRegistration extends com.google.gwt.event.shared.HandlerRegistration, com.google.web.bindery.event.shared.HandlerRegistration {
	@Override
	void removeHandler();
}
