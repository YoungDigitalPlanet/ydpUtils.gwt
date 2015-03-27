package eu.ydp.gwtutil.client.debug.gwtlogger;

/**
 * 
 * @deprecated use {@link eu.ydp.gwtutil.client.debug.log.Logger} instead
 */
@Deprecated
public class Logger implements ILogger {

	java.util.logging.Logger logger;

	@Deprecated
	public Logger() {
		this.logger = java.util.logging.Logger.getLogger("");
	}

	@Override
	@Deprecated
	public void fine(String message) {
		this.logger.fine(message);
	}

	@Deprecated
	public void fineIf(boolean azzert, String message) {
		if (azzert) {
			fine(message);
		}
	}

	@Override
	@Deprecated
	public void info(String message) {
		this.logger.info(message);

	}

	@Deprecated
	public void infoIf(boolean azzert, String message) {
		if (azzert) {
			info(message);
		}
	}

	@Override
	@Deprecated
	public void severe(String message) {
		this.logger.severe(message);
	}

	@Deprecated
	public void severeIf(boolean azzert, String message) {
		if (azzert) {
			severe(message);
		}
	}

	@Override
	@Deprecated
	public void warning(String message) {
		this.logger.warning(message);
	}

	@Deprecated
	public void warningIf(boolean azzert, String message) {
		if (azzert) {
			warning(message);
		}
	}

	@Deprecated
	public void methodLog(String className, String methodName, Object... args) {
		StringBuilder builder = new StringBuilder();
		builder.append("Calling: ").append(className).append(".").append(methodName);

		if (args != null) {
			appendArguments(builder, args);
		}

		this.logger.info(builder.toString());
	}

	private void appendArguments(StringBuilder builder, Object... args) {
		builder.append(" with args: ");
		for (Object argument : args) {
			if (argument == null) {
				builder.append("null, ");
			} else {
				builder.append("{@").append(argument.hashCode()).append("@ - ").append(argument.toString()).append("},");
			}
		}
	}
}
