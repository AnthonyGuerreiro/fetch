package fetch.test.task.splitter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Before;
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

    private void clearShowSearcherEntries() {
        ShowSearcher showSearcher = getTask(ShowSearcher.class).get();
        showSearcher.getEntries().clear();
    }

    private void fullyExecuteShowSearcher() throws ConfigurationException {
        ShowSearcher showSearcher = getTask(ShowSearcher.class).get();
        showSearcher.execute();
        showSearcher.onFinish();
    }

    private void finishExecuteShowSearcher() {
        ShowSearcher showSearcher = getTask(ShowSearcher.class).get();
        showSearcher.onFinish();
    }

    @Before
    public void init() {
        Optional<ShowSearcher> showSearcher = getTask(ShowSearcher.class);
        assertTrue(showSearcher.isPresent());
    }

    @Test
    public void testEpisodeSplitterSingleEntry() throws ConfigurationException {
        clearShowSearcherEntries();
        addEntryToShowSearcher("show", "profile", "single.entry");
        finishExecuteShowSearcher();

        EpisodeSplitter epSplitter = getTask(EpisodeSplitter.class).get();
        epSplitter.execute();

        Set<List<Entry>> splittedShows = epSplitter.getSplittedEntries();

        System.out.println("BEGIN!");
        splittedShows.stream().flatMap(show -> show.stream()).collect(Collectors.toList())
                .forEach(t -> System.out.println(t.getTitle()));
        System.out.println("END!");

        long count = splittedShows.stream().flatMap(show -> show.stream()).count();

        assertEquals(1l, count);
    }
}
