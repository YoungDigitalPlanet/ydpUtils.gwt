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

	public void fineIf(boolean azzert,String message) {
		if(azzert){
			fine(message);
		}
	}

	@Override
	public void info(String message) {
		this.logger.info(message);

	}

	public void infoIf(boolean azzert,String message) {
		if(azzert){
			info(message);
		}

	}

	@Override
	public void severe(String message) {
		this.logger.severe(message);
	}


	public void severeIf(boolean azzert,String message) {
		if(azzert){
		  severe(message);
		}
	}

	@Override
	public void warning(String message) {
		this.logger.warning(message);
	}

	public void warningIf(boolean azzert, String message){
		if(azzert){
			warning(message);
		}
	}

	public void methodLog(String className, String methodName, Object ... args){
		StringBuilder builder = new StringBuilder();
		builder.append("Calling: ")
			.append(className)
			.append(".")
			.append(methodName);

		if(args != null){
			appendArguments(builder, args);
		}

		this.logger.info(builder.toString());
	}

	private void appendArguments(StringBuilder builder, Object... args) {
		builder.append(" with args: ");
		for (Object argument : args) {
			if(argument == null){
				builder.append("null, ");
			}else{
				builder.append("{@")
					.append(argument.hashCode())
					.append("@ - ")
					.append(argument.toString())
					.append("},");
			}
		}
	}
}
