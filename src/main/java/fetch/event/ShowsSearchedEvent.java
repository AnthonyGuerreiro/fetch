package fetch.event;

import java.util.List;
import java.util.Map;

import fetch.event.core.Event;
import fetch.event.core.EventHandler;
import fetch.event.handler.ShowsSearchedEventHandler;
import fetch.profile.Show;
import fetch.task.searcher.Entry;

public class ShowsSearchedEvent implements Event {

    private Map<Show, List<Entry>> entries;

    public ShowsSearchedEvent(Map<Show, List<Entry>> entries) {
        this.entries = entries;
    }

    @Override
    public Class<? extends EventHandler> getEventHandlerType() {
        return ShowsSearchedEventHandler.class;
    }

    public Map<Show, List<Entry>> getEntries() {
        return entries;
    }
}
