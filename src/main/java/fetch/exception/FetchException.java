package fetch.exception;

public class FetchException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = -8447327874413963876L;

    public FetchException() {
        super();
    }

    public FetchException(String msg) {
        super(msg);
    }

    public FetchException(Throwable cause) {
        super(cause);
    }

    public FetchException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
