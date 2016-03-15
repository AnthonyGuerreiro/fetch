package fetch.task.download.rpc;

import fetch.task.download.TorrentInfo;

public class TransmissionTorrentInfo implements TorrentInfo {

    private nl.stil4m.transmission.api.domain.TorrentInfo info;

    public TransmissionTorrentInfo(nl.stil4m.transmission.api.domain.TorrentInfo info) {
        this.info = info;
    }

    @Override
    public Long getId() {
        return info.getId();
    }

    @Override
    public String getName() {
        return info.getName();
    }
}
