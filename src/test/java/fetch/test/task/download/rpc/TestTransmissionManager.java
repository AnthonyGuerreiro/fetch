package fetch.test.task.download.rpc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import fetch.exception.ConfigurationException;
import fetch.exception.DownloadManagerException;
import fetch.plugin.PluginLoader;
import fetch.task.download.DownloadManager;
import fetch.task.download.TorrentInfo;

public class TestTransmissionManager {

    private final static String TEST_MAGNET_LINK = "magnet:?xt=urn:btih:727665E0FE70263CD0B715758C2E8DB9A78554EC&dn=white+house+down+2013+720p+brrip+x264+yify&tr=udp%3A%2F%2Ftracker.openbittorrent.com%3A80%2Fannounce&tr=udp%3A%2F%2Fopen.demonii.com%3A1337";

    private DownloadManager downloader;

    @Before
    public void init() throws ConfigurationException {
        try {
            downloader = getDownloader();
        } catch (ConfigurationException e) {
            String msg = "Failed to initialize test case";
            throw new ConfigurationException(msg, e);
        }
    }

    private TorrentInfo addTorrent(boolean prepare) throws DownloadManagerException {
        if (!prepare) {
            return downloader.addTorrent(TEST_MAGNET_LINK);
        }
        try {
            return downloader.addTorrent(TEST_MAGNET_LINK);
        } catch (DownloadManagerException e) {
            String msg = "Failed to add torrent to prepare a test case";
            throw new DownloadManagerException(msg, e);
        }
    }

    private void removeTorrent(TorrentInfo info, boolean cleanup)
            throws DownloadManagerException {
        if (!cleanup) {
            downloader.removeTorrent(info.getId());
        } else {
            try {
                downloader.removeTorrent(info.getId());
            } catch (DownloadManagerException e) {
                String msg = "Failed to remove torrent with id(" + info.getId()
                        + ") and name (" + info.getName() + ") in test cleanup phase";
                throw new DownloadManagerException(msg, e);
            }
        }
    }

    @Test
    public void testAddTorrent() throws DownloadManagerException {
        TorrentInfo info = addTorrent(false);
        assertNotNull(info.getId());
        assertEquals("white+house+down+2013+720p+brrip+x264+yify", info.getName());
        removeTorrent(info, true);
    }

    @Test
    public void testRemoveTorrent() throws DownloadManagerException {
        TorrentInfo info = addTorrent(true);
        removeTorrent(info, false);
    }

    private DownloadManager getDownloader() throws ConfigurationException {
        return PluginLoader.getInstance().getPlugin(DownloadManager.class);
    }
}
