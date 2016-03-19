package fetch.task.searcher.rss;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import fetch.exception.FetchException;
import fetch.log.LogManager;
import fetch.log.Logger;
import fetch.task.searcher.Crawler;
import fetch.task.searcher.Entry;

public class RssCrawler implements Crawler {

    private static final Logger logger = LogManager.getLogger(RssCrawler.class);

    @Override
    public List<Entry> getEntries(String url) throws FetchException {

        try {
            return _getEntries(url);
        } catch (Exception e) {
            String msg = "Unable to fetch feeds from " + url
                    + ". Proceeding with no matches";
            throw new FetchException(msg, e);
        }

    }

    private List<Entry> _getEntries(String url)
            throws IllegalArgumentException, FeedException, IOException {

        URL feedUrl = new URL(url);
        HttpURLConnection httpcon = (HttpURLConnection) feedUrl.openConnection();

        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(httpcon));
        Iterator<SyndEntry> entries = feed.getEntries().iterator();

        List<Entry> resultEntries = new ArrayList<>();
        while (entries.hasNext()) {
            resultEntries.add(new RssEntry(entries.next()));
        }
        return resultEntries;
    }
}
