package fetch.task.reader;

import java.util.List;
import java.util.Set;

import fetch.conf.Configuration;
import fetch.conf.ConfigurationKey;
import fetch.exception.ConfigurationException;
import fetch.plugin.PluginLoader;
import fetch.profile.Profile;
import fetch.profile.reader.ProfilesReader;
import fetch.task.Task;

public class Reader implements Task {

    @Override
    public void execute() throws ConfigurationException {
        List<Profile> profiles = getProfiles();
    }

    private List<Profile> getProfiles() throws ConfigurationException {

        ConfigurationKey key = ConfigurationKey.PROFILES_FILE;

        String filename = Configuration.getInstance().get(key.getProperty(), key.getDefaultValue());
        Set<ProfilesReader> profileReaders = new PluginLoader().load(ProfilesReader.class);
        // TODO enforce only one reader
        return profileReaders.iterator().next().getProfiles(filename);
    }

    @Override
    public int getOrder() {
        return 100;
    }

}
