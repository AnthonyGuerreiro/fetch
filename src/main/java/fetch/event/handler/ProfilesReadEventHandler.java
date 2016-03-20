package fetch.event.handler;

import fetch.annotation.Indexable;
import fetch.event.ProfilesReadEvent;
import fetch.event.core.EventHandler;

@Indexable
public interface ProfilesReadEventHandler extends EventHandler {
    void handle(ProfilesReadEvent event);
}
