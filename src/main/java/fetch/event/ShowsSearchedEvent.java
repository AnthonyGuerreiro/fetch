package fetch.event;

import fetch.event.core.Event;
import fetch.event.core.EventHandler;
import fetch.event.handler.ShowsSearchedEventHandler;

public class ShowsSearchedEvent implements Event {

    @Override
    public Class<? extends EventHandler> getEventHandlerType() {
        return ShowsSearchedEventHandler.class;
    }

}
