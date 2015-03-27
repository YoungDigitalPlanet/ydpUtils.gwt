package eu.ydp.gwtutil.client.timer;

import com.google.common.base.Optional;

public class TimerAccessibleMock implements Timer {

	private static Optional<TimerAccessibleMock> INSTANCE = Optional.absent();
	private static boolean cancelled;

	public static TimerAccessibleMock getInstance() {
		return INSTANCE.get();
	}

	public static void reset() {
		if (INSTANCE.isPresent()) {
			INSTANCE = Optional.absent();
		}
		cancelled = false;
	}

	public static boolean hasBeenCancelled() {
		return cancelled;
	}

	private Runnable timerAction;

	@Override
	public void init(final Runnable timerAction) {
		initFields();
		this.timerAction = timerAction;
	}

	@Override
	public void scheduleRepeating(int delayMillis) {
		cancelled = false;
	}

	@Override
	public void cancel() {
		cancelled = true;
	}

	public void dispatch() {
		if (!cancelled) {
			timerAction.run();
		}
	}

	private void initFields() {
		if (TimerAccessibleMock.INSTANCE.isPresent()) {
			throw new IllegalStateException("TimerAccessibleMock statics should be cleared before next use. "
					+ "Maybe cancel() was expected but not invoked. Static reset() is requered before next unit test.");
		}
		TimerAccessibleMock.INSTANCE = Optional.of(this);
	}
}
