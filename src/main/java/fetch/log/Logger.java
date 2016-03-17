package fetch.log;

public class Logger {

    private org.apache.logging.log4j.Logger logger;

    public Logger(org.apache.logging.log4j.Logger logger) {
        this.logger = logger;
    }

    public void trace(String msg) {
        logger.trace(msg);
    }

    public void trace(Exception e) {
        logger.trace(e);
    }

    public void trace(String msg, Exception e) {
        logger.trace(msg, e);
    }

    public void debug(String msg) {
        logger.debug(msg);
    }

    public void debug(Exception e) {
        logger.debug(e);
    }

    public void debug(String msg, Exception e) {
        logger.debug(msg, e);
    }

    public void info(String msg) {
        logger.info(msg);
    }

    public void info(Exception e) {
        logger.info(e);
    }

    public void info(String msg, Exception e) {
        logger.info(msg, e);
    }

    public void warn(String msg) {
        logger.warn(msg);
    }

    public void warn(Exception e) {
        logger.warn(e);
    }

    public void warn(String msg, Exception e) {
        logger.warn(msg, e);
    }

    public void error(String msg) {
        logger.error(msg);
    }

    public void error(Exception e) {
        logger.error(e);
    }

    public void error(String msg, Exception e) {
        logger.error(msg, e);
    }

    public void fatal(String msg) {
        logger.fatal(msg);
    }

    public void fatal(Exception e) {
        logger.fatal(e);
    }

    public void fatal(String msg, Exception e) {
        logger.fatal(msg, e);
    }
}
