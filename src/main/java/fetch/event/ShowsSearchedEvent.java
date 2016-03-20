package fetch.event;

import java.util.List;

import fetch.event.core.Event;
import fetch.event.core.EventHandler;
import fetch.event.handler.ShowsSearchedEventHandler;
import fetch.task.searcher.Entry;

public class ShowsSearchedEvent implements Event {

    private List<Entry> entries;

    public ShowsSearchedEvent(List<Entry> entries) {
        this.entries = entries;
    }

    @Override
    public Class<? extends EventHandler> getEventHandlerType() {
        return ShowsSearchedEventHandler.class;
    }

    public List<Entry> getShows() {
        return entries;
    }
}
