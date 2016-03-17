package fetch.log;

public class LogManager {

    public static Logger getLogger(Class<?> klass) {
        return new Logger(org.apache.logging.log4j.LogManager.getLogger(klass));
    }
}
