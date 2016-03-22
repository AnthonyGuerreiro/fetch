package fetch.plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

import fetch.annotation.Indexable;
import fetch.conf.Configuration;
import fetch.conf.ConfigurationMap;
import fetch.exception.ConfigurationException;
import fetch.exception.FetchRuntimeException;
import fetch.log.LogManager;
import fetch.log.Logger;
import fetch.message.Messages;

public class PluginLoader {

    private static Logger logger;

    private static PluginLoader instance = new PluginLoader();

    private Map<Class<? super Plugin>, List<? extends Plugin>> plugins = new HashMap<>();

    public static PluginLoader getInstance() {
        return instance;
    }

    public <T extends Plugin> List<T> getPlugins(Class<T> type) {
        return getPlugins(type, true);
    }

    public <T extends Plugin> List<T> getPlugins(Class<T> type, boolean mandatoryPlugin) {

        List<? extends Plugin> plugins = this.plugins.get(type);
        if (mandatoryPlugin && (plugins == null || plugins.isEmpty())) {
            String msg = new Messages().get("pg.not.found", type,
                    Configuration.CONFIGURATION_FILE);
            throw new NullPointerException(msg);
        }
        if (!mandatoryPlugin && (plugins == null || plugins.isEmpty())) {
            return new ArrayList<>();
        }
        return plugins.stream().map(plugin -> (T) plugin).collect(Collectors.toList());
    }

    public <T extends Plugin> T getPlugin(Class<T> type) {
        return getPlugins(type, true).get(0);
    }

    private PluginLoader() {
        try {
            init();
        } catch (ConfigurationException e) {
            String msg = new Messages().get("pg.fail.load");
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
            logger.trace("pg.found", plugin.getClass());
        }
        return plugins;
    }

    private List<Class<Plugin>> getPluginTypes() throws ConfigurationException {

        ConfigurationMap map = Configuration.getInstance().getMap();
        List<String> interfaceNames = map.getIndexablePlugins();

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
                String msg = new Messages().get("pg.interface.not.plugin", className,
                        Plugin.class);
                throw new ConfigurationException(msg);
            }
            if (!klass.isAnnotationPresent(Indexable.class)) {
                String msg = new Messages().get("pg.cannot.be.listed", className);
                throw new ConfigurationException(msg);
            }
            return (Class<Plugin>) klass;
        } catch (ClassNotFoundException e) {
            String msg = new Messages().get("pg.class.not.found", className,
                    Configuration.CONFIGURATION_FILE);
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

        Indexable indexable = type.getAnnotation(Indexable.class);
        if (indexable.isSingleInstance() && pluginsByType.size() != 1) {
            String msg = new Messages().get("pg.is.not.single", type,
                    pluginsByType.size());
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
