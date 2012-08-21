package eu.ydp.gwtutil.client.debug.logger;


/**
 * Logger that uses custom functionalities, 
 * configured through .gwt.xml file, 
 * see YdpGwtUtil.gwt.xml
 */
public interface Logger {

	public abstract void log(String text);

}