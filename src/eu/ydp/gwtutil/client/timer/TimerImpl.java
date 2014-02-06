package eu.ydp.gwtutil.client.timer;

public class TimerImpl implements Timer {

	private com.google.gwt.user.client.Timer timer;

	@Override
	public void init(final Runnable timerAction) {
		timer = new com.google.gwt.user.client.Timer() {

			@Override
			public void run() {
				timerAction.run();
			}
		};
	}

	@Override
	public void scheduleRepeating(int delayMillis) {
		timer.cancel();
		timer.scheduleRepeating(delayMillis);
	}

	@Override
	public void cancel() {
		if (timer != null) {
			timer.cancel();
		}
	}

}
