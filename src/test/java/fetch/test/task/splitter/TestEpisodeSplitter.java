package fetch.test.task.splitter;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import fetch.exception.ConfigurationException;
import fetch.profile.Profile;
import fetch.profile.Show;
import fetch.task.searcher.Entry;
import fetch.task.searcher.ShowSearcher;
import fetch.task.splitter.EpisodeSplitter;
import fetch.test.AbstractTest;

public class TestEpisodeSplitter extends AbstractTest {

    private void addEntryToShowSearcher(String show, String profile, String entry) {

        Profile _profile = getProfile(profile);
        Show _show = getShow(show, _profile);
        Entry _entry = getEntry(entry);

        ShowSearcher showSearcher = getTask(ShowSearcher.class).get();
        Map<Show, List<Entry>> entries = showSearcher.getEntries();
        List<Entry> list = entries.get(_show);
        if (list == null) {
            list = new ArrayList<>();
            entries.put(_show, list);
        }
        list.add(_entry);
    }

    private void fullyExecuteShowSearcher() throws ConfigurationException {
        ShowSearcher showSearcher = getTask(ShowSearcher.class).get();
        showSearcher.execute();
        showSearcher.onFinish();
    }

    @Test
    public void testEpisodeSplitterSingleEntry() throws ConfigurationException {

        addEntryToShowSearcher("show", "profile", "single.entry");
        fullyExecuteShowSearcher();

        EpisodeSplitter epSplitter = getTask(EpisodeSplitter.class).get();
        epSplitter.execute();

        Set<List<Entry>> splittedShows = epSplitter.getSplittedEntries();
        long count = splittedShows.stream().flatMap(show -> show.stream()).count();
        assertEquals(1l, count);
    }
}
