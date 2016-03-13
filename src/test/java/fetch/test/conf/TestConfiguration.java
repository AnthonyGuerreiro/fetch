package fetch.test.conf;

import static fetch.conf.ConfigurationKey.PROFILES_FILE;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import fetch.conf.Configuration;;

public class TestConfiguration {

    @Test
    public void testReadProfileFile() {
        Configuration conf = Configuration.getInstance();
        String profileFile = conf.get(PROFILES_FILE.getProperty());
        assertNotNull("Could not find property " + PROFILES_FILE.getProperty()
                + " on default configuration", profileFile);
    }
}
