package fetch.test.task.searcher.rss;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;

import fetch.exception.FetchException;
import fetch.task.searcher.Crawler;
import fetch.task.searcher.Entry;
import fetch.task.searcher.rss.RssCrawler;

public class TestRssSearcher {

    private Crawler crawler = new RssCrawler();
    private final static String TEST_URL = "http://torrentz.com/feed?q=";

    @Test
    public void testGenericSearch() throws FetchException {
        List<Entry> entries = crawler.getEntries(TEST_URL);
        String msg = "Could not find any entry on generic search";
        assertFalse(msg, entries.isEmpty());
    }
}
