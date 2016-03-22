package fetch.conf;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import fetch.event.handler.ProfilesReadEventHandler;
import fetch.event.handler.ShowsSearchedEventHandler;
import fetch.task.Task;
import fetch.task.download.DownloadManager;
import fetch.task.filter.Filter;
import fetch.task.reader.ProfilesReader;
import fetch.task.searcher.Crawler;

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
        indexablePlugins.add(Task.class.getCanonicalName());
    }

    private void addCrawlers() {
        indexablePlugins.add(Crawler.class.getCanonicalName());
    }

    private void addFilters() {
        indexablePlugins.add(Filter.class.getCanonicalName());
    }

    private void addDownloadManager() {
        indexablePlugins.add(DownloadManager.class.getCanonicalName());
    }

    private void addProfileReader() {
        indexablePlugins.add(ProfilesReader.class.getCanonicalName());
    }

    private void addEvents() {

        indexablePlugins.add(ProfilesReadEventHandler.class.getCanonicalName());
        indexablePlugins.add(ShowsSearchedEventHandler.class.getCanonicalName());
    }
}
