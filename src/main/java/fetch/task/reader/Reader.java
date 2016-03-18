package fetch.task.reader;

import java.util.List;

import fetch.conf.Configuration;
import fetch.exception.ConfigurationException;
import fetch.log.LogManager;
import fetch.log.Logger;
import fetch.plugin.PluginLoader;
import fetch.profile.Profile;
import fetch.task.Task;

public class Reader implements Task {

    private final static Logger logger = LogManager.getLogger(Reader.class);

    @Override
    public void execute() throws ConfigurationException {
        logger.info("Starting task " + getClass().getSimpleName());

        logger.info("Reading profiles from " + getProfilesFilename());
        List<Profile> profiles = getProfiles();
    }

    public ProfilesReader getProfilesReader() throws ConfigurationException {
        return PluginLoader.getInstance().getPlugin(ProfilesReader.class);
    }

    private List<Profile> getProfiles() throws ConfigurationException {

        String filename = getProfilesFilename();
        ProfilesReader profilesReaders = getProfilesReader();
        return profilesReaders.getProfiles(filename);
    }

    private String getProfilesFilename() {
        return Configuration.getInstance().getMap().getProfilesFile();
    }

    @Override
    public int getOrder() {
        return 100;
    }

}
