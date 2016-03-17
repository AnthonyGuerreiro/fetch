package fetch.test.task.searcher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import fetch.task.searcher.Crawler;
import fetch.task.searcher.ShowSearcher;
import fetch.task.searcher.rss.RssCrawler;

public class TestShowSearcher {

    @Test
    public void testLoadCrawlers() {
        List<Crawler> crawlers = new ShowSearcher().getCrawlers();
        assertEquals(1, crawlers.size());
        assertTrue(crawlers.get(0) instanceof RssCrawler);
    }
}
