package fetch.test.profile;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;

import fetch.plugin.PluginLoader;
import fetch.profile.reader.ProfileReader;

public class TestProfileReader {

    private Set<ProfileReader> loadProfileReaders() {
        return new PluginLoader().load(ProfileReader.class);
    }

    @Test
    public void testLoadProfileReader() {
        Set<ProfileReader> profileReaders = loadProfileReaders();
        assertEquals(1, profileReaders.size());
    }
}
