package fetch.task.download.rpc;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import fetch.exception.DownloadManagerException;
import fetch.task.download.BitTorrentClient;
import fetch.task.download.TorrentInfo;
import nl.stil4m.transmission.api.TransmissionRpcClient;
import nl.stil4m.transmission.api.domain.AddTorrentInfo;
import nl.stil4m.transmission.api.domain.AddedTorrentInfo;
import nl.stil4m.transmission.api.domain.RemoveTorrentInfo;
import nl.stil4m.transmission.api.domain.ids.NumberListIds;
import nl.stil4m.transmission.rpc.RpcClient;
import nl.stil4m.transmission.rpc.RpcConfiguration;
import nl.stil4m.transmission.rpc.RpcException;

public class Transmission implements BitTorrentClient {

	private TransmissionRpcClient client;

	public Transmission(String url) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		RpcConfiguration rpcConfiguration = new RpcConfiguration();
		rpcConfiguration.setHost(URI.create(url));
		RpcClient client = new RpcClient(rpcConfiguration, objectMapper);
		this.client = new TransmissionRpcClient(client);
	}

	@Override
	public TorrentInfo addTorrent(byte[] base64Torrent) throws DownloadManagerException {
		// TODO implement
		throw new UnsupportedOperationException();
	}

	@Override
	public List<TorrentInfo> getAllTorrentsInfo() throws DownloadManagerException {
		try {
			List<TorrentInfo> infos = new ArrayList<>();
			List<nl.stil4m.transmission.api.domain.TorrentInfo> torrentsInfo = client.getAllTorrentsInfo()
					.getTorrents();

			for (nl.stil4m.transmission.api.domain.TorrentInfo info : torrentsInfo) {
				infos.add(new TransmissionTorrentInfo(info));
			}
			return infos;
		} catch (RpcException e) {
			throw new DownloadManagerException("Unable to get torrents info", e);
		}
	}

	@Override
	public TorrentInfo addTorrent(String magnetLink) throws DownloadManagerException {

		try {

			AddTorrentInfo torrentInfo = new AddTorrentInfo();
			torrentInfo.setFilename(magnetLink);

			AddedTorrentInfo result = client.addTorrent(torrentInfo);
			TorrentInfo info = new TransmissionTorrentInfo(result.getTorrentAdded());

			if (info.getId() == null) {
				String msg = "Could not add torrent with magnetLink " + magnetLink;
				throw new DownloadManagerException(msg);
			}
			return info;
		} catch (RpcException e) {
			String msg = "Failed to add torrent with magnetLink " + magnetLink;
			throw new DownloadManagerException(msg, e);
		}
	}

	@Override
	public void removeTorrent(Long... ids) throws DownloadManagerException {
		try {
			client.removeTorrent(new RemoveTorrentInfo(new NumberListIds(ids), true));
		} catch (RpcException e) {
			String msg = "Failed to remove torrent(s) with ids " + ids;
			throw new DownloadManagerException(msg);
		}
	}

}
