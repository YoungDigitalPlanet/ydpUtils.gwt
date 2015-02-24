package eu.ydp.gwtutil.client.event.animation;

public class MozEventTypes implements EventTypes {

	@Override
	public String getAnimationEnd() {
		return "animationend";
	}

	@Override
	public String getTransistionEnd() {
		return "transitionend";
	}
}
