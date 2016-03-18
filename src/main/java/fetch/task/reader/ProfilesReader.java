package fetch.task.reader;

import java.util.List;

import fetch.annotation.Listable;
import fetch.exception.ConfigurationException;
import fetch.plugin.Plugin;
import fetch.profile.Profile;

@Listable(isSingleInstance = true)
public interface ProfilesReader extends Plugin {
    List<Profile> getProfiles(String filename) throws ConfigurationException;
}
