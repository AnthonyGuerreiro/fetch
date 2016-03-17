package fetch.task.filter;

import java.util.List;

import fetch.plugin.HasOrder;
import fetch.profile.Show;
import fetch.task.searcher.Entry;

public interface Filter extends HasOrder {
    void filter(Show show, List<Entry> entries);
}
