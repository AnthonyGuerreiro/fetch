package fetch.test.profile.reader.xml;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import fetch.conf.ConfigurationKey;
import fetch.exception.ConfigurationException;
import fetch.task.reader.ProfilesReader;
import fetch.task.reader.node.Profile;
import fetch.task.reader.node.Show;
import fetch.task.reader.xml.XMLProfilesReader;

public class TestXMLProfilesReader {

    private List<Profile> getProfiles() throws ConfigurationException {
        return getProfiles(ConfigurationKey.PROFILES_FILE.getDefaultValue());
    }

    private List<Profile> getProfiles(String filename) throws ConfigurationException {
        ProfilesReader profileReader = new XMLProfilesReader();
        return profileReader.getProfiles(filename);
    }

    @Test
    public void testFindAllProfiles() throws ConfigurationException {
        List<Profile> profiles = getProfiles();
        assertEquals(2, profiles.size());

        Profile tvShowsProfile = profiles.get(0);
        Profile animeProfile = profiles.get(1);

        assertEquals("tv shows", tvShowsProfile.getName());
        assertEquals("anime", animeProfile.getName());
    }

    @Test
    public void testReadCorrectTvShows() throws ConfigurationException {
        Profile tvShowsProfile = getProfiles().get(0);
        List<Show> tvShows = tvShowsProfile.getShows();

        assertEquals(2, tvShows.size());

        assertEquals("The Big Bang Theory", tvShows.get(0).getName());
        assertEquals("The Mentalist", tvShows.get(1).getName());

    }

    @Test
    public void testReadCorrectAnime() throws ConfigurationException {
        Profile animeProfile = getProfiles().get(1);
        List<Show> anime = animeProfile.getShows();

        assertEquals(2, anime.size());

        assertEquals("Log Horizon", anime.get(0).getName());
        assertEquals("Another", anime.get(1).getName());
    }
}
