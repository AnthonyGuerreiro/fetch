package fetch.test.task.reader.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fetch.conf.Configuration;
import fetch.exception.ConfigurationException;
import fetch.profile.Profile;
import fetch.profile.Show;
import fetch.task.reader.ProfilesReader;
import fetch.task.reader.xml.XMLProfilesReader;

public class TestXMLProfilesReader {

    private List<Profile> profiles;
    private Profile tvShowsProfile;
    private Profile animeProfile;

    private List<Profile> getProfiles() throws ConfigurationException {
        String filename = Configuration.getInstance().getMap().getProfilesFile();
        ProfilesReader profileReader = new XMLProfilesReader();
        return profileReader.getProfiles(filename);
    }

    @Before
    public void init() throws ConfigurationException {
        profiles = getProfiles();

        assertEquals("There should be 2 profiles", 2, profiles.size());
        tvShowsProfile = profiles.get(0);
        animeProfile = profiles.get(1);
    }

    @Test
    public void testFindCorrectProfiles() throws ConfigurationException {
        assertEquals("tv shows", tvShowsProfile.getName());
        assertEquals("anime", animeProfile.getName());
    }

    @Test
    public void testReadCorrectTvShows() throws ConfigurationException {
        List<Show> tvShows = tvShowsProfile.getShows();

        assertEquals(2, tvShows.size());

        assertEquals("The Big Bang Theory", tvShows.get(0).getName());
        assertEquals("The Mentalist", tvShows.get(1).getName());

        assertEquals(tvShowsProfile, tvShows.get(0).getParent());
        assertEquals(tvShowsProfile, tvShows.get(1).getParent());
    }

    @Test
    public void testReadCorrectAnime() throws ConfigurationException {
        List<Show> anime = animeProfile.getShows();

        assertEquals(2, anime.size());

        assertEquals("Log Horizon", anime.get(0).getName());
        assertEquals("Another", anime.get(1).getName());

        assertEquals(animeProfile, anime.get(0).getParent());
        assertEquals(animeProfile, anime.get(1).getParent());
    }

    @Test
    public void testReadCorrectBegin() {
        Show logHorizon = animeProfile.getShows().get(0);
        assertTrue("Log Horizon tv show should have a begin",
                logHorizon.getBegin().isPresent());
        assertEquals("1", logHorizon.getBegin().get());

        Show another = animeProfile.getShows().get(1);
        assertFalse("Another tv show should not have a begin",
                another.getBegin().isPresent());
    }

    @Test
    public void testReadCorrectSubtitles() {
        Show logHorizon = animeProfile.getShows().get(0);
        assertTrue("Log Horizon tv show should have subtitles",
                logHorizon.getSubtitles().isPresent());
        assertEquals("english", logHorizon.getSubtitles().get());

        Show another = animeProfile.getShows().get(1);
        assertFalse("Another tv show should not have subtitles",
                another.getSubtitles().isPresent());
    }
}
