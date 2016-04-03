package fetch.event.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import fetch.log.LogManager;
import fetch.log.Logger;
import fetch.plugin.PluginLoader;

public class EventDispatcher {

    private static EventDispatcher instance = new EventDispatcher();

    private final static Logger logger = LogManager.getLogger(EventDispatcher.class);

    private EventDispatcher() {

    }

    public static EventDispatcher getInstance() {
        return instance;
    }

    public <E extends Event> void dispatch(E event) {

        String methodName = "handle";
        Class<?> eventType = event.getClass();
        Class<? extends EventHandler> handlerType = event.getEventHandlerType();
        try {
            Method method = event.getEventHandlerType().getMethod(methodName,
                    event.getClass());

            List<? extends EventHandler> handlers = PluginLoader.getInstance()
                    .getPlugins(handlerType);

            if (handlers.isEmpty()) {
                logger.info("ev.handler.not.found", eventType, handlerType);
            }

            handlers.forEach(handler -> invoke(handler, method, event));
        } catch (NoSuchMethodException | SecurityException e) {
            logger.error("ev.handler.method.event", methodName, eventType, handlerType);
        }
    }

    private <E extends Event> void invoke(EventHandler handler, Method method, E event) {
        try {
            method.invoke(handler, event);
        } catch (IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {

            logger.error(e, "ev.error.handling", event.getClass(), handler.getClass());
        }
    }
}
