package fetch.task.searcher;

import java.util.List;

import fetch.annotation.Indexable;
import fetch.exception.FetchException;
import fetch.plugin.Plugin;

@Indexable
public interface Crawler extends Plugin {
    List<Entry> getEntries(String url) throws FetchException;
}
