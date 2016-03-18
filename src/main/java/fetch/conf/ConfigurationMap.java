package fetch.conf;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import fetch.task.download.Downloader;
import fetch.task.filter.CosineSimilarityFilter;
import fetch.task.filter.ShowFilter;
import fetch.task.reader.Reader;
import fetch.task.searcher.ShowSearcher;
import fetch.task.searcher.rss.RssCrawler;

public class ConfigurationMap {

    private String profilesFile = "profiles.xml";
    private List<String> listableInterfaces = new ArrayList<>();

    private String transmissionHost = "localhost";
    private int transmissionPort = 9091;

    public ConfigurationMap() {
        init();
    }

    private void init() {
        addTasks();
        addCrawlers();
        addFilters();
    }

    private void addTasks() {
        listableInterfaces.add(Reader.class.getCanonicalName());
        listableInterfaces.add(ShowSearcher.class.getCanonicalName());
        listableInterfaces.add(ShowFilter.class.getCanonicalName());
        listableInterfaces.add(Downloader.class.getCanonicalName());
    }

    private void addCrawlers() {
        listableInterfaces.add(RssCrawler.class.getCanonicalName());
    }

    private void addFilters() {
        listableInterfaces.add(CosineSimilarityFilter.class.getCanonicalName());
    }

    @JsonProperty("listable.interfaces")
    public List<String> getListableInterfaces() {
        return listableInterfaces;
    }

    public void setListableInterfaces(List<String> listableInterfaces) {
        this.listableInterfaces = listableInterfaces;
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
}
