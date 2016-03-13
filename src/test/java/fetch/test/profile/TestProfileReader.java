package fetch.test.profile;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;

import fetch.plugin.PluginLoader;
import fetch.profile.reader.ProfilesReader;

public class TestProfileReader {

    private Set<ProfilesReader> loadProfileReaders() {
        return new PluginLoader().load(ProfilesReader.class);
    }

    @Test
    public void testLoadProfileReader() {
        Set<ProfilesReader> profileReaders = loadProfileReaders();
        assertEquals(1, profileReaders.size());
    }
}
