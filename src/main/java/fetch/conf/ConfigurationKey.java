package fetch.conf;

public enum ConfigurationKey {
    PROFILE_FILE("profile.file");

    private String property;

    private ConfigurationKey(String property) {
        this.property = property;
    }

    public String getProperty() {
        return property;
    }
}
