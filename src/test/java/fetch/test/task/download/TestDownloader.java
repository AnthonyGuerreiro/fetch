package fetch.test.task.download;

import org.junit.Test;

import fetch.task.download.Downloader;

public class TestDownloader {

    @Test
    public void testLoadDownloader() {
        Downloader task = new Downloader();
        task.getDownloader();
    }
}
