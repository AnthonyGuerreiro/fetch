package fetch.plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

import fetch.annotation.Listable;
import fetch.conf.Configuration;
import fetch.conf.ConfigurationMap;
import fetch.exception.ConfigurationException;
import fetch.exception.FetchRuntimeException;
import fetch.log.LogManager;
import fetch.log.Logger;

public class PluginLoader {

    private static Logger logger;

    private static PluginLoader instance = new PluginLoader();

    private Map<Class<? super Plugin>, List<? extends Plugin>> plugins = new HashMap<>();

    public static PluginLoader getInstance() {
        return instance;
    }

    public <T extends Plugin> List<T> getPlugins(Class<T> type) {
        List<? extends Plugin> plugins = this.plugins.get(type);
        if (plugins == null) {
            String name = type.getCanonicalName();
            String msg = "Could not find any plugin with type " + name + ". Make sure "
                    + name + " is Listable, there are plugins that implement it, and "
                    + name + " is listed in " + Configuration.CONFIGURATION_FILE
                    + " as listable.interfaces";
            throw new NullPointerException(msg);
        }
        return plugins.stream().map(plugin -> (T) plugin).collect(Collectors.toList());
    }

    public <T extends Plugin> T getPlugin(Class<T> type) {
        return getPlugins(type).get(0);
    }

    private PluginLoader() {
        try {
            init();
        } catch (ConfigurationException e) {
            String msg = "Failed to load plugins";
            throw new FetchRuntimeException(msg, e);
        }
    }

    private void init() throws ConfigurationException {

        logger = LogManager.getLogger(PluginLoader.class);

        List<Plugin> plugins = loadPlugins();
        List<Class<Plugin>> pluginTypes = getPluginTypes();

        mapPluginsByType(pluginTypes, plugins);

    }

    private List<Plugin> loadPlugins() {
        List<Plugin> plugins = new ArrayList<>();
        ServiceLoader<Plugin> pluginsLoaded = ServiceLoader.load(Plugin.class);
        for (Plugin plugin : pluginsLoaded) {
            plugins.add(plugin);
            logger.trace("Plugin found: " + plugin.getClass().getCanonicalName());
        }
        return plugins;
    }

    private List<Class<Plugin>> getPluginTypes() throws ConfigurationException {

        ConfigurationMap map = Configuration.getInstance().getMap();
        List<String> interfaceNames = map.getListableInterfaces();

        List<Class<Plugin>> interfaces = new ArrayList<>(interfaceNames.size());
        for (String interfaceName : interfaceNames) {
            Class<Plugin> klass = getPluginType(interfaceName);
            interfaces.add(klass);
        }
        return interfaces;
    }

    private Class<Plugin> getPluginType(String className) throws ConfigurationException {

        try {
            Class<?> klass = Class.forName(className);
            if (!Plugin.class.isAssignableFrom(klass)) {
                String msg = "Interface " + className + " is not a "
                        + Plugin.class.getCanonicalName();
                throw new ConfigurationException(msg);
            }
            if (!klass.isAnnotationPresent(Listable.class)) {
                String msg = "Interface " + className
                        + " cannot be listed in listable.interfaces";
                throw new ConfigurationException(msg);
            }
            return (Class<Plugin>) klass;
        } catch (ClassNotFoundException e) {
            String msg = "Cannot find interface " + className + " from "
                    + Configuration.CONFIGURATION_FILE + " listable.interfaces";
            throw new ConfigurationException(msg);
        }
    }

    private void mapPluginsByType(List<Class<Plugin>> pluginTypes, List<Plugin> plugins)
            throws ConfigurationException {

        for (Class<Plugin> type : pluginTypes) {

            List<Plugin> pluginsByType = plugins.stream()
                    .filter(plugin -> type.isAssignableFrom(plugin.getClass()))
                    .collect(Collectors.toList());

            validateSingleInstances(type, pluginsByType);

            if (HasOrder.class.isAssignableFrom(type)) {
                sort(pluginsByType);
            }

            this.plugins.put(type, pluginsByType);
        }
    }

    private void validateSingleInstances(Class<Plugin> type, List<Plugin> pluginsByType)
            throws ConfigurationException {

        Listable listable = type.getAnnotation(Listable.class);
        if (listable.isSingleInstance() && pluginsByType.size() != 1) {
            String msg = "Expected 1 plugin of type " + type.getCanonicalName()
                    + ". Found <" + pluginsByType.size() + ">.";
            throw new ConfigurationException(msg);
        }
    }

    private void sort(List<Plugin> instances) {

        List<HasOrder> orderedInstances = instances.stream()
                .map(instance -> (HasOrder) instance).collect(Collectors.toList());

        orderedInstances.sort((t1, t2) -> t1.getOrder() - t2.getOrder());

        instances.clear();
        instances.addAll(orderedInstances);
    }

}
