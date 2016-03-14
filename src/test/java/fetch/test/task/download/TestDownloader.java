package fetch.test.task.download;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;

import fetch.plugin.PluginLoader;
import fetch.task.download.RPCDownloader;

public class TestDownloader {

    private Set<RPCDownloader> loadDownloaders() {
        return new PluginLoader().load(RPCDownloader.class);
    }

    @Test
    public void testLoadDownloader() {

        Set<RPCDownloader> downloaders = loadDownloaders();
        assertEquals(1, downloaders.size());
    }
}
