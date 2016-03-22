package fetch.test.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fetch.exception.ConfigurationException;
import fetch.task.reader.Reader;
import fetch.task.searcher.ShowSearcher;
import fetch.test.AbstractTest;

public class TestEvents extends AbstractTest {

    @Test
    public void testProfilesReadEventHandler() throws ConfigurationException {
        Reader reader = getTask(Reader.class).get();
        ShowSearcher showSearcher = getTask(ShowSearcher.class).get();

        assertTrue(reader.getProfiles().isEmpty());
        assertTrue(showSearcher.getProfiles().isEmpty());
        reader.execute();
        assertFalse(reader.getProfiles().isEmpty());
        assertTrue(showSearcher.getProfiles().isEmpty());
        reader.onFinish();
        assertEquals(reader.getProfiles(), showSearcher.getProfiles());
    }
}
