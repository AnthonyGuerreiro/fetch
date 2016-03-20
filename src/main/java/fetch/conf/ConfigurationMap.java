package fetch.conf;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import fetch.event.handler.ProfilesReadEventHandler;
import fetch.task.download.Downloader;
import fetch.task.download.rpc.TransmissionManager;
import fetch.task.filter.CosineSimilarityFilter;
import fetch.task.filter.ShowFilter;
import fetch.task.reader.Reader;
import fetch.task.reader.xml.XMLProfilesReader;
import fetch.task.searcher.ShowSearcher;
import fetch.task.searcher.rss.RssCrawler;

public class ConfigurationMap {

    private String profilesFile = "profiles.xml";
    private List<String> indexablePlugins = new ArrayList<>();

    private String transmissionHost = "localhost";
    private int transmissionPort = 9091;

    public ConfigurationMap() {
        init();
    }

    private void init() {
        addTasks();
        addCrawlers();
        addFilters();
        addDownloadManager();
        addProfileReader();
        addEvents();
    }

    @JsonProperty("indexable.plugins")
    public List<String> getIndexablePlugins() {
        return indexablePlugins;
    }

    public void setIndexablePlugins(List<String> indexablePlugins) {
        this.indexablePlugins = indexablePlugins;
    }

    @JsonProperty("profiles.file")
    public String getProfilesFile() {
        return profilesFile;
    }

    public void setProfilesFile(String profilesFile) {
        this.profilesFile = profilesFile;
    }

    @JsonProperty("transmission.host")
    public String getTransmissionHost() {
        return transmissionHost;
    }

    public void setTransmissionHost(String transmissionHost) {
        this.transmissionHost = transmissionHost;
    }

    @JsonProperty("transmission.port")
    public int getTransmissionPort() {
        return transmissionPort;
    }

    public void setTransmissionPort(int transmissionPort) {
        this.transmissionPort = transmissionPort;
    }

    private void addTasks() {
        indexablePlugins.add(Reader.class.getCanonicalName());
        indexablePlugins.add(ShowSearcher.class.getCanonicalName());
        indexablePlugins.add(ShowFilter.class.getCanonicalName());
        indexablePlugins.add(Downloader.class.getCanonicalName());
    }

    private void addCrawlers() {
        indexablePlugins.add(RssCrawler.class.getCanonicalName());
    }

    private void addFilters() {
        indexablePlugins.add(CosineSimilarityFilter.class.getCanonicalName());
    }

    private void addDownloadManager() {
        indexablePlugins.add(TransmissionManager.class.getCanonicalName());
    }

    private void addProfileReader() {
        indexablePlugins.add(XMLProfilesReader.class.getCanonicalName());
    }

    private void addEvents() {
        indexablePlugins.add(ProfilesReadEventHandler.class.getCanonicalName());
    }
}
