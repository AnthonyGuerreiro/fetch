package fetch.task.filter;

import java.util.List;

import fetch.annotation.Indexable;
import fetch.plugin.HasOrder;
import fetch.profile.Show;
import fetch.task.searcher.Entry;

@Indexable
public interface Filter extends HasOrder {
    void filter(Show show, List<Entry> entries);
}
