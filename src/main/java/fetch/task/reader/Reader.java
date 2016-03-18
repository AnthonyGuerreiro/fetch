package fetch.task.reader;

import java.util.List;

import fetch.conf.Configuration;
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
        return PluginLoader.getInstance().getPlugin(ProfilesReader.class);
    }

    private List<Profile> getProfiles() throws ConfigurationException {

        String filename = Configuration.getInstance().getMap().getProfilesFile();
        ProfilesReader profilesReaders = getProfilesReader();
        return profilesReaders.getProfiles(filename);
    }

    @Override
    public int getOrder() {
        return 100;
    }

}
