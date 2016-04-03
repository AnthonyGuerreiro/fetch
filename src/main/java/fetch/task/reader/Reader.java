package fetch.task.reader;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import fetch.annotation.TestMethod;
import fetch.conf.Configuration;
import fetch.event.ProfilesReadEvent;
import fetch.event.core.EventDispatcher;
import fetch.exception.ConfigurationException;
import fetch.log.LogManager;
import fetch.log.Logger;
import fetch.profile.Profile;
import fetch.task.Task;

@Component
public class Reader implements Task {

    private final static Logger logger = LogManager.getLogger(Reader.class);

    private List<Profile> profiles = new ArrayList<>();

    private ProfilesReader profilesReader;

    @Inject
    public Reader(ProfilesReader profilesReader) {
        this.profilesReader = profilesReader;
    }

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

    public ProfilesReader getProfilesReader() {
        return profilesReader;
    }

    @Override
    public void onFinish() {
        logger.info("tk.finish.task", getClass().getSimpleName());
        EventDispatcher.getInstance().dispatch((new ProfilesReadEvent(profiles)));
    }

    @Override
    public int getOrder() {
        return 100;
    }

}
