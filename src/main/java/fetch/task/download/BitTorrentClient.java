package fetch.task.download;

import java.util.List;

import fetch.exception.DownloadManagerException;
import fetch.exception.FetchException;

public interface BitTorrentClient {

	/**
	 * Adds torrent with {@code magnetLink} to the download list.<br />
	 * Returns info of the AddedTorrentInfo with the {@code magnetLink}.
	 *
	 * @param magnetLink
	 * @return info of the add TorrentInfo with the {@code magnetLink}
	 * @throws FetchException
	 *             if there is an issue connecting to the
	 *             {@link BitTorrentClient} or adding the {@code magnetLink}
	 */
	TorrentInfo addTorrent(String magnetLink) throws DownloadManagerException;

	/**
	 * Adds the {@code base64Torrent} to the download list.<br />
	 * Returns info of the TorrentInfo with the {@code base64Torrent}.
	 *
	 * @param base64Torrent
	 * @return info of the add AddedTorrentInfo with the {@code base64Torrent}
	 * @throws FetchException
	 *             if there is an issue connecting to the
	 *             {@link BitTorrentClient} or adding the {@code base64Torrent}
	 */
	TorrentInfo addTorrent(byte[] base64Torrent) throws DownloadManagerException;

	/**
	 * Returns all {@link TorrentInfo}.
	 *
	 * @return all {@link TorrentInfo}
	 * @throws FetchException
	 *             if there is an issue connecting to the
	 *             {@link BitTorrentClient} or retrieving the torrents info
	 */
	List<TorrentInfo> getAllTorrentsInfo() throws DownloadManagerException;

	/**
	 * Removes the torrents with the {@code ids}.
	 *
	 * @param ids
	 * @throws FetchException
	 *             if there is an issue connecting to the
	 *             {@link BitTorrentClient} or removing the torrents
	 */
	void removeTorrent(Long... ids) throws DownloadManagerException;

}
