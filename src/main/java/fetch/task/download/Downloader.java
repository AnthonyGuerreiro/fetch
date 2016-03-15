package fetch.task.download;

import fetch.exception.ConfigurationException;
import fetch.plugin.PluginLoader;
import fetch.task.Task;

public class Downloader implements Task {

	@Override
	public void execute() throws ConfigurationException {
		DownloadManager downloader = getDownloader();
	}

	public DownloadManager getDownloader() throws ConfigurationException {
		return new PluginLoader().loadSingle(DownloadManager.class);
	}

	@Override
	public int getOrder() {
		return 300;
	}

}
