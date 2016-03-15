package fetch.test.task.download;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;

import fetch.plugin.PluginLoader;
import fetch.task.download.DownloadManager;

public class TestDownloader {

    private Set<DownloadManager> loadDownloaders() {
        return new PluginLoader().load(DownloadManager.class);
    }

    @Test
    public void testLoadDownloader() {

        Set<DownloadManager> downloaders = loadDownloaders();
        assertEquals(1, downloaders.size());
    }
}
