package eu.ydp.gwtutil.client.scheduler;

import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;

public class SchedulerMockImpl implements Scheduler {

	@Override
	public void scheduleDeferred(ScheduledCommand cmd) {
		cmd.execute();

	}

	@Override
	public void scheduleEntry(RepeatingCommand cmd) {
		cmd.execute();

	}

	@Override
	public void scheduleEntry(ScheduledCommand cmd) {
		cmd.execute();

	}

	@Override
	public void scheduleFinally(RepeatingCommand cmd) {
		cmd.execute();

	}

	@Override
	public void scheduleFinally(ScheduledCommand cmd) {
		cmd.execute();

	}

	@Override
	public void scheduleFixedDelay(RepeatingCommand cmd, int delayMs) {
		cmd.execute();

	}

	@Override
	public void scheduleFixedPeriod(RepeatingCommand cmd, int delayMs) {
		cmd.execute();

	}

	@Override
	public void scheduleIncremental(RepeatingCommand cmd) {
		cmd.execute();

	}

}
