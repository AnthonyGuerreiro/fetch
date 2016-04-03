package fetch.task.download;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import fetch.exception.ConfigurationException;
import fetch.log.LogManager;
import fetch.log.Logger;
import fetch.task.Task;

@Component
public class Downloader implements Task {

    private final static Logger logger = LogManager.getLogger(Downloader.class);

    private DownloadManager downloadManager;

    @Inject
    public Downloader(DownloadManager downloadManager) {
        this.downloadManager = downloadManager;
    }

    @Override
    public void execute() throws ConfigurationException {
        logger.info("tk.starting.task", getClass().getSimpleName());
        DownloadManager downloader = getDownloader();
    }

    public DownloadManager getDownloader() {
        return downloadManager;
    }

    @Override
    public void onFinish() {
        logger.info("tk.finish.task", getClass().getSimpleName());
        // TODO implement
    }

    @Override
    public int getOrder() {
        return 500;
    }

}
