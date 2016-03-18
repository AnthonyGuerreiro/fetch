package fetch.task.download;

import fetch.exception.ConfigurationException;
import fetch.log.LogManager;
import fetch.log.Logger;
import fetch.plugin.PluginLoader;
import fetch.task.Task;

public class Downloader implements Task {

    private final static Logger logger = LogManager.getLogger(Downloader.class);

    @Override
    public void execute() throws ConfigurationException {
        logger.info("Starting task " + getClass().getSimpleName());
        DownloadManager downloader = getDownloader();
    }

    public DownloadManager getDownloader() throws ConfigurationException {
        return PluginLoader.getInstance().getPlugin(DownloadManager.class);
    }

    @Override
    public int getOrder() {
        return 400;
    }

}
