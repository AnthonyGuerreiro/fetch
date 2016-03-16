package fetch.test.task.searcher;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;

import fetch.plugin.PluginLoader;
import fetch.task.searcher.Crawler;

public class TestShowSearcher {

    @Test
    public void testLoadCrawlers() {
        Set<Crawler> crawlers = new PluginLoader().load(Crawler.class);
        assertEquals(1, crawlers.size());
    }
}
