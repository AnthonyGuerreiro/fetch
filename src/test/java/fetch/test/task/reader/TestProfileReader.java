package fetch.test.task.reader;

import org.junit.Test;

import fetch.exception.ConfigurationException;
import fetch.task.reader.Reader;

public class TestProfileReader {

    @Test
    public void testLoadProfileReader() throws ConfigurationException {
        new Reader().getProfilesReader();
    }
}
