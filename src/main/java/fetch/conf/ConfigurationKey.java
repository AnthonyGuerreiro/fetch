package fetch.conf;

public enum ConfigurationKey {
    PROFILE_FILE("profile.file", "profiles.xml");

    private String property;
    private String defaultValue;

    private ConfigurationKey(String property, String defaultValue) {
        this.property = property;
        this.defaultValue = defaultValue;
    }

    public String getProperty() {
        return property;
    }

    public String getDefaultValue() {
        return defaultValue;
    }
}
