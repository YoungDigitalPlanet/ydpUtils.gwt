package eu.ydp.gwtutil.client.proxy;

import com.google.gwt.user.client.Timer;

/**
 * Proxy class for {@link Timer}. Allows easy mocking.
 * 
 * Additionally {@link #schedule(int)} accepts non-positive delay value.
 * 
 * @author rrybacki
 * 
 */
public abstract class TimerProxy {

	Timer timer;

	/**
	 * @see Timer#run()
	 */
	public abstract void run();

	/**
	 * Proxy for {@link Timer#schedule(int)}.
	 * 
	 * Accepts non-positive delay value - in such case {@link #run()} is called immediately.
	 * 
	 * @param delayMillis
	 *            Delay in ms or non-positive value for immediate execution.
	 * @see Timer#schedule(int)
	 */
	public void schedule(int delayMillis) {
		if (delayMillis > 0) {
			ensureTimer();
			timer.schedule(delayMillis);
		} else {
			run();
		}
	}

	/**
	 * Proxy for {@link Timer#scheduleRepeating(int)}.
	 * 
	 * @see Timer#scheduleRepeating(int)
	 */
	public void scheduleRepeating(int periodMillis) {
		ensureTimer();
		timer.scheduleRepeating(periodMillis);
	}

	/**
	 * @see Timer#cancel()
	 */
	public void cancel() {
		if (timer != null) {
			timer.cancel();
		}
	}

	/**
	 * Lazy initialization of the timer.
	 */
	void ensureTimer() {
		if (timer == null) {
			timer = new Timer() {

				@Override
				public void run() {
					TimerProxy.this.run();
				}
			};
		}
	}
}
