package eu.ydp.gwtutil.client.animation.js;


public class MockFrameworkAnimation implements FrameworkAnimation {

	private FrameworkAnimationListener listener;

	@Override
	public void setListener(FrameworkAnimationListener listener) {
		this.listener = listener;
	}

	@Override
	public void run(int duration) {
		if (listener != null){
			listener.onUpdate(1);
		}
	}

	@Override
	public void cancel() {
	}

}
