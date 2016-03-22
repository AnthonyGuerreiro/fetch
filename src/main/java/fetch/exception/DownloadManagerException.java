package fetch.exception;

import fetch.conf.Configuration;
import fetch.message.Messages;

public class DownloadManagerException extends FetchException {

    /**
     *
     */
    private static final long serialVersionUID = 3218813214773227650L;

    private static String appendedMessage = new Messages().get("dl.connect.bittorrent",
            Configuration.CONFIGURATION_FILE);

    public DownloadManagerException() {
        super(appendedMessage);
    }

    public DownloadManagerException(String msg) {
        super(msg + appendedMessage);
    }

    public DownloadManagerException(Throwable cause) {
        super(cause);
    }

    public DownloadManagerException(String msg, Throwable cause) {
        super(msg + appendedMessage, cause);
    }
}
