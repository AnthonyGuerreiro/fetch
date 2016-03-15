package fetch.task.download.rpc;

import java.util.List;

import fetch.conf.Configuration;
import fetch.exception.DownloadManagerException;
import fetch.task.download.BitTorrentClient;
import fetch.task.download.DownloadManager;
import fetch.task.download.TorrentInfo;

public class TransmissionManager implements DownloadManager {

	private BitTorrentClient client;

	public TransmissionManager() {
		initializeClient(getConnectionUrl());
	}

	@Override
	public TorrentInfo addTorrent(byte[] base64Torrent) throws DownloadManagerException {
		return client.addTorrent(base64Torrent);
	}

	@Override
	public TorrentInfo addTorrent(String magnetLink) throws DownloadManagerException {
		return client.addTorrent(magnetLink);
	}

	@Override
	public List<TorrentInfo> getAllTorrentsInfo() throws DownloadManagerException {
		return client.getAllTorrentsInfo();
	}

	private String getConnectionUrl() {
		Configuration conf = Configuration.getInstance();
		String host = conf.get("transmission.host", "localhost");
		int port = conf.get("transmission.port", 9091);
		return String.format("http://%s:%d/transmission/rpc", host, port);
	}

	private void initializeClient(String url) {
		client = new Transmission(url);
	}

	@Override
	public BitTorrentClient getClient() {
		return client;
	}

	@Override
	public void removeTorrent(Long... ids) throws DownloadManagerException {
		client.removeTorrent(ids);
	}

}