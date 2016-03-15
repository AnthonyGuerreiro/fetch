package fetch.exception;

import fetch.conf.Configuration;

public class DownloadManagerException extends FetchException {

	/**
	 *
	 */
	private static final long serialVersionUID = 3218813214773227650L;

	private static String appendedMessage = ". Make sure your BitTorrent client is connected and setup to receive remote RPCs and you have specified the good parameters in "
			+ Configuration.CONFIGURATION_FILE;

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
