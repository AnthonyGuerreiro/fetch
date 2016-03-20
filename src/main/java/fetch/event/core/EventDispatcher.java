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
                    .getPlugins(handlerType, false);

            if (handlers.isEmpty()) {
                String msg = "Dispatched event of type " + eventType.getCanonicalName()
                        + " but there was no handler of type "
                        + handlerType.getCanonicalName() + " to process it";
                logger.info(msg);
            }

            handlers.forEach(handler -> invoke(handler, method, event));
        } catch (NoSuchMethodException | SecurityException e) {
            String msg = "Could not find " + methodName + "("
                    + eventType.getCanonicalName() + ") on class " + handlerType;
            logger.error(msg);

        }
    }

    private <E extends Event> void invoke(EventHandler handler, Method method, E event) {
        try {
            method.invoke(handler, event);
        } catch (IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {

            String msg = "Error handling event " + event.getClass().getCanonicalName()
                    + " on handler " + handler.getClass().getCanonicalName();
            logger.error(msg, e);
        }
    }
}
