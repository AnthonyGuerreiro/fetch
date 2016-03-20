package fetch.event.core;

public interface Event {
    Class<? extends EventHandler> getEventHandlerType();
}