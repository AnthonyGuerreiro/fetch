package fetch.plugin;

import java.util.HashSet;
import java.util.ServiceLoader;
import java.util.Set;

import fetch.exception.ConfigurationException;

public class PluginLoader {

    public <T> Set<T> load(Class<T> type) {

        ServiceLoader<T> loader = ServiceLoader.load(type);
        Set<T> instances = new HashSet<>();
        for (T instance : loader) {
            instances.add(instance);
        }
        return instances;
    }

    public <T> T loadSingle(Class<T> type) throws ConfigurationException {

        Set<T> instances = load(type);
        if (instances.size() == 1) {
            return instances.iterator().next();
        }

        String msg = "Expected 1 plugin of type " + type.getCanonicalName() + ". Found <"
                + instances.size() + ">.";
        throw new ConfigurationException(msg);
    }

}
