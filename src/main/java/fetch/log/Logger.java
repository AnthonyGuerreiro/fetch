package fetch.log;

import fetch.message.Messages;

public class Logger {

    private org.apache.logging.log4j.Logger logger;

    private Messages messages = new Messages();

    public Logger(org.apache.logging.log4j.Logger logger) {
        this.logger = logger;
    }

    private String getMessage(String msg, Object... args) {
        return messages.get(msg, args);
    }

    public void trace(String msg, Object... args) {
        logger.trace(getMessage(msg, args));
    }

    public void trace(Exception e) {
        logger.trace(e);
    }

    public void trace(Exception e, String msg, Object... args) {
        logger.trace(getMessage(msg, args), e);
    }

    public void debug(String msg, Object... args) {
        logger.debug(getMessage(msg, args));
    }

    public void debug(Exception e) {
        logger.debug(e);
    }

    public void debug(Exception e, String msg, Object... args) {
        logger.debug(getMessage(msg, args), e);
    }

    public void info(String msg, Object... args) {
        logger.info(getMessage(msg, args));
    }

    public void info(Exception e) {
        logger.info(e);
    }

    public void info(Exception e, String msg, Object... args) {
        logger.info(getMessage(msg, args), e);
    }

    public void warn(String msg, Object... args) {
        logger.warn(getMessage(msg, args));
    }

    public void warn(Exception e) {
        logger.warn(e);
    }

    public void warn(Exception e, String msg, Object... args) {
        logger.warn(getMessage(msg, args), e);
    }

    public void error(String msg, Object... args) {
        logger.error(getMessage(msg, args));
    }

    public void error(Exception e) {
        logger.error(e);
    }

    public void error(Exception e, String msg, Object... args) {
        logger.error(getMessage(msg, args), e);
    }

    public void fatal(String msg, Object... args) {
        logger.fatal(getMessage(msg, args));
    }

    public void fatal(Exception e) {
        logger.fatal(e);
    }

    public void fatal(Exception e, String msg, Object... args) {
        logger.fatal(getMessage(msg, args), e);
    }

}
