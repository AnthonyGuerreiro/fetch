package fetch.event.handler;

import fetch.annotation.Indexable;
import fetch.event.ShowsSearchedEvent;
import fetch.event.core.EventHandler;

@Indexable
public interface ShowsSearchedEventHandler extends EventHandler {
    void handle(ShowsSearchedEvent event);
}
