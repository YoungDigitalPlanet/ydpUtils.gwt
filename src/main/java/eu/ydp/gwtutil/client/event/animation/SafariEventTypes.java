package eu.ydp.gwtutil.client.event.animation;

public class SafariEventTypes implements EventTypes {
    @Override
    public String getAnimationEnd() {
        return "webkitAnimationEnd";
    }

    @Override
    public String getTransistionEnd() {
        return "webkitTransitionEnd";
    }
}
