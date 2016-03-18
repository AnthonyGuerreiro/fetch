package fetch.conf;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConfigurationMap {

    private String profilesFile = "profiles.xml";
    private List<String> listableInterfaces = new ArrayList<>();

    private String transmissionHost = "localhost";
    private int transmissionPort = 9091;

    public ConfigurationMap() {
        init();
    }

    private void init() {
        // TODO fill listable listable interfaces
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
