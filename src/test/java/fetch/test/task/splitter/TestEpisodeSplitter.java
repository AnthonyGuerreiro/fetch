package fetch.test.task.splitter;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import fetch.exception.ConfigurationException;
import fetch.task.searcher.Entry;
import fetch.task.searcher.ShowSearcher;
import fetch.task.splitter.EpisodeSplitter;
import fetch.test.AbstractTest;

public class TestEpisodeSplitter extends AbstractTest {

    private void addEntryToShowSearcher(Entry entry) {
        ShowSearcher showSearcher = getTask(ShowSearcher.class).get();
        showSearcher.getEntries().add(entry);
    }

    private void fullyExecuteShowSearcher() throws ConfigurationException {
        ShowSearcher showSearcher = getTask(ShowSearcher.class).get();
        showSearcher.execute();
        showSearcher.onFinish();
    }

    @Test
    public void testEpisodeSplitterSingleEntry() throws ConfigurationException {

        Entry entry = getEntry("single.entry");

        addEntryToShowSearcher(entry);
        fullyExecuteShowSearcher();

        EpisodeSplitter epSplitter = getTask(EpisodeSplitter.class).get();
        epSplitter.execute();

        Set<List<Entry>> splittedShows = epSplitter.getSplittedEntries();
        long count = splittedShows.stream().flatMap(show -> show.stream()).count();
        assertEquals(1l, count);
    }
}
