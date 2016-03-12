package fetch.test.conf;

import static fetch.conf.ConfigurationKey.PROFILE_FILE;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import fetch.conf.Configuration;;

public class TestConfiguration {

    @Test
    public void testReadProfileFile() {
        Configuration conf = Configuration.getInstance();
        String profileFile = conf.get(PROFILE_FILE.getProperty());
        assertNotNull("Could not find property " + PROFILE_FILE.getProperty()
                + " on default configuration", profileFile);
    }
}
