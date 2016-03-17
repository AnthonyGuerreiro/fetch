package fetch.test.task.reader;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import fetch.plugin.PluginLoader;
import fetch.task.reader.ProfilesReader;

public class TestProfileReader {

    private List<ProfilesReader> loadProfileReaders() {
        return new PluginLoader().load(ProfilesReader.class);
    }

    @Test
    public void testLoadProfileReader() {
        List<ProfilesReader> profileReaders = loadProfileReaders();
        assertEquals(1, profileReaders.size());
    }
}
