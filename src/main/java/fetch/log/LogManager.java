package fetch.log;

public class LogManager {
    public static Logger getLogger(Class<?> klass) {
        return new Logger();
    }
}
