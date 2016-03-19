package fetch.task.reader;

import java.util.List;

import fetch.annotation.Indexable;
import fetch.exception.ConfigurationException;
import fetch.plugin.Plugin;
import fetch.profile.Profile;

@Indexable(isSingleInstance = true)
public interface ProfilesReader extends Plugin {
    List<Profile> getProfiles(String filename) throws ConfigurationException;
}
