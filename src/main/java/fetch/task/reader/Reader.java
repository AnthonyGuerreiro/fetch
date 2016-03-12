package fetch.task.reader;

import java.util.List;
import java.util.Set;

import fetch.conf.Configuration;
import fetch.conf.ConfigurationKey;
import fetch.exception.ConfigurationException;
import fetch.plugin.PluginLoader;
import fetch.profile.Profile;
import fetch.profile.reader.ProfileReader;
import fetch.task.Task;

public class Reader implements Task {

    @Override
    public void execute() throws ConfigurationException {
        List<Profile> profiles = getProfiles();
    }

    private List<Profile> getProfiles() throws ConfigurationException {

        ConfigurationKey key = ConfigurationKey.PROFILE_FILE;

        String filename = Configuration.getInstance().get(key.getProperty(), key.getDefaultValue());
        Set<ProfileReader> profileReaders = new PluginLoader().load(ProfileReader.class);
        // TODO enforce only one reader
        return profileReaders.iterator().next().getProfiles(filename);
    }

    @Override
    public int getOrder() {
        return 100;
    }

}
