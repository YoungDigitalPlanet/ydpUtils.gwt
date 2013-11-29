package eu.ydp.gwtutil.client.debug.log;


public class ConsoleAppender implements LogAppender {

	@Override
	public void appendeMessage(String message) {
		writeToconsole(message);
	}

	private native void writeToconsole(String msg)/*-{
		console.log(msg);
	}-*/;
}
