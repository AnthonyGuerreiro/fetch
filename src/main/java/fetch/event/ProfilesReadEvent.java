package fetch.event;

import java.util.List;

import fetch.event.core.Event;
import fetch.event.core.EventHandler;
import fetch.event.handler.ProfilesReadEventHandler;
import fetch.profile.Profile;

public class ProfilesReadEvent implements Event {

    private List<Profile> profiles;

    public ProfilesReadEvent(List<Profile> profiles) {
        this.profiles = profiles;
    }

    @Override
    public Class<? extends EventHandler> getEventHandlerType() {
        return ProfilesReadEventHandler.class;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

}
