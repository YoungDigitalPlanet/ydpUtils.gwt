package eu.ydp.gwtutil.client.timer;

public interface Timer {

	void init(Runnable timerAction);
	
	void scheduleRepeating(int delayMillis);

	void cancel();

}
