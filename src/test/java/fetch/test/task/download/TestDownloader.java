package fetch.test.task.download;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import fetch.task.download.Downloader;
import fetch.test.AbstractTest;

public class TestDownloader extends AbstractTest {

    @Before
    public void init() {
        Optional<Downloader> downloader = getTask(Downloader.class);
        assertTrue(downloader.isPresent());
    }

    private Downloader getDownloaderTask() {
        return getTask(Downloader.class).get();
    }

    @Test
    public void testLoadDownloader() {
        assertNotNull(getDownloaderTask().getDownloader());
    }
}
