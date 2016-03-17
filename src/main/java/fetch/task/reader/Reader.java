package fetch.task.reader;

import java.util.List;

import fetch.conf.Configuration;
import fetch.conf.ConfigurationKey;
import fetch.exception.ConfigurationException;
import fetch.plugin.PluginLoader;
import fetch.profile.Profile;
import fetch.task.Task;

public class Reader implements Task {

    @Override
    public void execute() throws ConfigurationException {
        List<Profile> profiles = getProfiles();
    }

    public ProfilesReader getProfilesReader() throws ConfigurationException {
        return new PluginLoader().loadSingle(ProfilesReader.class);
    }

    private List<Profile> getProfiles() throws ConfigurationException {

        ConfigurationKey key = ConfigurationKey.PROFILES_FILE;

        String filename = Configuration.getInstance().get(key.getProperty(),
                key.getDefaultValue());
        ProfilesReader profilesReaders = getProfilesReader();
        return profilesReaders.getProfiles(filename);
    }

    @Override
    public int getOrder() {
        return 100;
    }

}
