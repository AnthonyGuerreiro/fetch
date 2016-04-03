package fetch.event.handler;

import fetch.event.ShowsSearchedEvent;
import fetch.event.core.EventHandler;

public interface ShowsSearchedEventHandler extends EventHandler {
    void handle(ShowsSearchedEvent event);
}
