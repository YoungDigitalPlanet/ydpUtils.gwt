package eu.ydp.gwtutil.client.event.animation;


public class EventTypesDefault implements EventTypes {

	@Override
	public String getAnimationEnd() {
		return "animationend";
	}

	@Override
	public String getTransistionEnd() {
		return "transitionend";

	}
}
