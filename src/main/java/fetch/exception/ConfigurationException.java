package fetch.exception;

public class ConfigurationException extends FetchException {

    /**
     *
     */
    private static final long serialVersionUID = -1550249652377481991L;

    public ConfigurationException() {
        super();
    }

    public ConfigurationException(String msg) {
        super(msg);
    }

    public ConfigurationException(Throwable cause) {
        super(cause);
    }

    public ConfigurationException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
