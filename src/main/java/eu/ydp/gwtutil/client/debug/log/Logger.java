package eu.ydp.gwtutil.client.debug.log;

import com.google.inject.Inject;

public class Logger {

    private final LogLevel acceptedLevel;
    private final LogAppender appender;

    @Inject
    public Logger(LogAppender appender) {
        this.appender = appender;
        this.acceptedLevel = LogLevel.INFO;
    }

    public void info(String message) {
        writeLog(LogLevel.INFO, message);
    }

    public void info(Throwable throwable) {
        info(parseThrowable(throwable));
    }

    public void error(String message) {
        writeLog(LogLevel.ERROR, message);
    }

    public void error(Throwable throwable) {
        error(parseThrowable(throwable));
    }

    private void writeLog(LogLevel level, String message) {
        if (isLevelLogable(level)) {
            appender.appendMessage(message);
        }
    }

    private boolean isLevelLogable(LogLevel level) {
        return acceptedLevel.compareTo(level) <= 0;
    }

    private String parseThrowable(Throwable e) {
        StackTraceElement[] stackTrace = e.getStackTrace();
        String message = e.getMessage();

        StringBuilder sb = new StringBuilder(message);
        for (StackTraceElement stackTraceElement : stackTrace) {
            sb.append("\nFilename: ").append(stackTraceElement.getFileName()).append(" method: ").append(stackTraceElement.getMethodName()).append(" line: ")
                    .append(stackTraceElement.getLineNumber());
        }
        return sb.toString();
    }
}
