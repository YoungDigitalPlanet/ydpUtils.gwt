package eu.ydp.gwtutil.client.debug.log;


public class ConsoleAppender implements LogAppender {

	@Override
	public void appendMessage(String message) {
		writeToConsole(message);
	}

	private native void writeToConsole(String msg)/*-{
		console.log(msg);
	}-*/;
}
