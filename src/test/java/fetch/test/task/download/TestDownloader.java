package fetch.test.task.download;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import fetch.plugin.PluginLoader;
import fetch.task.download.DownloadManager;

public class TestDownloader {

    private List<DownloadManager> loadDownloaders() {
        return new PluginLoader().load(DownloadManager.class);
    }

    @Test
    public void testLoadDownloader() {

        List<DownloadManager> downloaders = loadDownloaders();
        assertEquals(1, downloaders.size());
    }
}
