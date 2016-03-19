package fetch.task.download;

import java.util.List;

import fetch.annotation.Indexable;
import fetch.exception.DownloadManagerException;
import fetch.exception.FetchException;
import fetch.plugin.Plugin;

@Indexable(isSingleInstance = true)
public interface DownloadManager extends Plugin {
    /**
     * Adds the {@code magnetLink} to Transmission and starts the download.
     * <br />
     * Returns torrent info of the torrent added.
     *
     * @param magnetLink
     * @return torrent info of the torrent added
     * @throws DownloadManagerException
     *             if there is an issue connecting to the
     *             {@link BitTorrentClient} or adding the {@code magnetLink}
     * @see BitTorrentClient
     */
    TorrentInfo addTorrent(String magnetLink) throws DownloadManagerException;

    /**
     * Adds the {@code base64Torrent} to Transmission and starts the download.
     * <br />
     * Returns torrent info of the torrent added.
     *
     * @param base64Torrent
     * @return torrent info of the torrent added
     * @throws DownloadManagerException
     *             if there is an issue connecting to the
     *             {@link BitTorrentClient} or adding the {@code base64Torrent}
     * @see BitTorrentClient
     */

    TorrentInfo addTorrent(byte[] base64Torrent) throws DownloadManagerException;

    /**
     * Returns all {@link TorrentInfo}.
     *
     * @return all {@link TorrentInfo}
     * @See BitTorrentClient
     * @throws DownloadManagerException
     *             if there is an issue connecting to the
     *             {@link BitTorrentClient} or retrieving the torrents info
     */
    List<TorrentInfo> getAllTorrentsInfo() throws DownloadManagerException;

    /**
     * Returns instance of the BitTorrent client.<br />
     * Used mostly for junit.
     *
     * @return instance of the BitTorrent client
     * @see BitTorrentClient
     */
    BitTorrentClient getClient();

    /**
     * Removes the torrents with the {@code ids}.
     *
     * @param ids
     * @throws FetchException
     * @see BitTorrentClient
     * @throws DownloadManagerException
     *             if there is an issue connecting to the
     *             {@link BitTorrentClient} or removing the torrents
     */
    void removeTorrent(Long... ids) throws DownloadManagerException;
}
