package fetch.test.task.download;

import org.junit.Test;

import fetch.exception.ConfigurationException;
import fetch.task.download.Downloader;

public class TestDownloader {

    @Test
    public void testLoadDownloader() throws ConfigurationException {
        new Downloader().getDownloader();
    }
}
