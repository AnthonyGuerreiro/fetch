package fetch.test.conf;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fetch.conf.Configuration;
import fetch.conf.ConfigurationMap;;

public class TestConfiguration {

    private ConfigurationMap defaultMap = new ConfigurationMap();

    @Test
    public void testReadProfileFile() {
        ConfigurationMap map = Configuration.getInstance().getMap();
        assertEquals("test.profiles.xml", map.getProfilesFile());
    }
}
