package fetch.test.task.reader;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fetch.exception.ConfigurationException;
import fetch.task.reader.Reader;
import fetch.task.searcher.ShowSearcher;
import fetch.test.AbstractTest;

public class TestProfileReader extends AbstractTest {

    @Test
    public void testLoadProfileReader() throws ConfigurationException {
        new Reader().getProfilesReader();
    }

    @Test
    public void testSimpleProfilesReadEventHandler() throws ConfigurationException {
        Reader reader = getTask(Reader.class).get();
        reader.getProfiles().add(getProfile("profile"));

        ShowSearcher showSearcher = getTask(ShowSearcher.class).get();
        assertEquals(0, showSearcher.getProfiles().size());
        reader.onFinish();
        assertEquals(1, showSearcher.getProfiles().size());
    }
}
