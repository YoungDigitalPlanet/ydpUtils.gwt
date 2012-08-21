package eu.ydp.gwtutil.client.debug.gwtlogger;


public class Logger implements ILogger {
	
	java.util.logging.Logger  logger;
	
	public Logger(){
		this.logger = java.util.logging.Logger.getLogger("");
	}
	
	@Override
	public void fine(String message) {
		this.logger.fine(message);
		
	}
	
	@Override
	public void info(String message) {
		this.logger.info(message);
		
	}
	
	@Override
	public void severe(String message) {
		this.logger.severe(message);
	}
	
	@Override
	public void warning(String message) {
		this.logger.warning(message);
	}
}
