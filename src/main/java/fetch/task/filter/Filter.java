package fetch.task.filter;

import java.util.List;

import fetch.annotation.Listable;
import fetch.plugin.HasOrder;
import fetch.profile.Show;
import fetch.task.searcher.Entry;

@Listable
public interface Filter extends HasOrder {
    void filter(Show show, List<Entry> entries);
}
