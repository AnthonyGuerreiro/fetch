package fetch.profile.reader;

import java.util.List;

import fetch.exception.ConfigurationException;
import fetch.profile.Profile;

public interface ProfilesReader {
    List<Profile> getProfiles(String filename) throws ConfigurationException;
}
