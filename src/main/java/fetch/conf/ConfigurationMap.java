package fetch.conf;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConfigurationMap {

    private String profilesFile = "profiles.xml";

    private String transmissionHost = "localhost";
    private int transmissionPort = 9091;

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
