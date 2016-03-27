package fetch.test.task.searcher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import fetch.exception.ConfigurationException;
import fetch.task.reader.Reader;
import fetch.task.searcher.Crawler;
import fetch.task.searcher.ShowSearcher;
import fetch.task.searcher.rss.RssCrawler;
import fetch.test.AbstractTest;

public class TestShowSearcher extends AbstractTest {

    private Reader reader;
    private ShowSearcher showSearcher;

    @Before
    public void init() {
        Optional<ShowSearcher> showSearcher = getTask(ShowSearcher.class);
        assertTrue(showSearcher.isPresent());
        this.showSearcher = showSearcher.get();

        Optional<Reader> reader = getTask(Reader.class);
        assertTrue(reader.isPresent());
        this.reader = reader.get();
    }

    @Test
    public void testLoadCrawlers() {
        List<Crawler> crawlers = showSearcher.getCrawlers();
        assertEquals(1, crawlers.size());
        assertTrue(crawlers.get(0) instanceof RssCrawler);
    }

    @Test
    public void testFoundEntries() throws ConfigurationException {
        reader.execute();
        reader.onFinish();

        showSearcher.execute();
        String msg = ShowSearcher.class.getSimpleName()
                + " should have found some entries";
        assertFalse(msg, showSearcher.getEntries().isEmpty());
    }
}
