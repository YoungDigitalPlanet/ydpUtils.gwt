package eu.ydp.gwtutil.client.animation.js;

public interface FrameworkAnimation {

	void setListener(FrameworkAnimationListener listener);

	void run(int duration);

	void cancel();

}