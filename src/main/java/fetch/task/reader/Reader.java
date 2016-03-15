package fetch.task.reader;

import java.util.List;

import fetch.conf.Configuration;
import fetch.conf.ConfigurationKey;
import fetch.exception.ConfigurationException;
import fetch.plugin.PluginLoader;
import fetch.task.Task;
import fetch.task.reader.node.Profile;

public class Reader implements Task {

    @Override
    public void execute() throws ConfigurationException {
        List<Profile> profiles = getProfiles();
    }

    private List<Profile> getProfiles() throws ConfigurationException {

        ConfigurationKey key = ConfigurationKey.PROFILES_FILE;

        String filename = Configuration.getInstance().get(key.getProperty(),
                key.getDefaultValue());
        ProfilesReader profilesReaders = new PluginLoader()
                .loadSingle(ProfilesReader.class);
        return profilesReaders.getProfiles(filename);
    }

    @Override
    public int getOrder() {
        return 100;
    }

}
