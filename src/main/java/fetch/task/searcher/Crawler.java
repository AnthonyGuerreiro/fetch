package fetch.task.searcher;

import java.util.List;

import fetch.exception.FetchException;

public interface Crawler {
    List<Entry> getEntries(String url) throws FetchException;
}
