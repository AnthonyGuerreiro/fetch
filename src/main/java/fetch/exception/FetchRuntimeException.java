package fetch.exception;

public class FetchRuntimeException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -6667547999085890682L;

    public FetchRuntimeException(String msg, Throwable e) {
        super(msg, e);
    }
}
