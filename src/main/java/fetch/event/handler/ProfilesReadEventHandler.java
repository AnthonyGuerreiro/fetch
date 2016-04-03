package fetch.event.handler;

import fetch.event.ProfilesReadEvent;
import fetch.event.core.EventHandler;

public interface ProfilesReadEventHandler extends EventHandler {
    void handle(ProfilesReadEvent event);
}
