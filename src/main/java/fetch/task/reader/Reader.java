package fetch.task.reader;

import java.util.ArrayList;
import java.util.List;

import fetch.annotation.TestMethod;
import fetch.conf.Configuration;
import fetch.event.ProfilesReadEvent;
import fetch.event.core.EventDispatcher;
import fetch.exception.ConfigurationException;
import fetch.log.LogManager;
import fetch.log.Logger;
import fetch.plugin.PluginLoader;
import fetch.profile.Profile;
import fetch.task.Task;

public class Reader implements Task {

    private final static Logger logger = LogManager.getLogger(Reader.class);

    private List<Profile> profiles = new ArrayList<>();

    @Override
    public void execute() throws ConfigurationException {
        logger.info("tk.starting.task", getClass().getSimpleName());

        logger.info("rd.reading.profiles", getProfilesFilename());
        readProfiles();
    }

    private void readProfiles() throws ConfigurationException {

        String filename = getProfilesFilename();
        ProfilesReader profilesReaders = getProfilesReader();
        profiles = profilesReaders.getProfiles(filename);
    }

    private String getProfilesFilename() {
        return Configuration.getInstance().getMap().getProfilesFile();
    }

    @TestMethod
    public List<Profile> getProfiles() {
        return profiles;
    }

    @TestMethod
    public ProfilesReader getProfilesReader() throws ConfigurationException {
        return PluginLoader.getInstance().getPlugin(ProfilesReader.class);
    }

    @Override
    public int getOrder() {
        return 100;
    }

    @Override
    public void onFinish() {
        EventDispatcher.getInstance().dispatch((new ProfilesReadEvent(profiles)));
    }

}
