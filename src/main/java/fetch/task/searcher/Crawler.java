package fetch.task.searcher;

import java.util.List;

import fetch.annotation.Listable;
import fetch.exception.FetchException;
import fetch.plugin.Plugin;

@Listable
public interface Crawler extends Plugin {
    List<Entry> getEntries(String url) throws FetchException;
}
