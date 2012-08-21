package eu.ydp.gwtutil.client.debug.gwtlogger;

/**
 * Logger that uses functionalities provided by GWK SDK, 
 * configured in Host Page through JavaScript, 
 * see YdpGwtUtil.gwt.xml
 */
public interface ILogger {

	void fine(String message);
	void info(String message);
	void severe(String message);
	void warning(String message);
}
