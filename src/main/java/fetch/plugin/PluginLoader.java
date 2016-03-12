package fetch.plugin;

import java.util.HashSet;
import java.util.ServiceLoader;
import java.util.Set;

public class PluginLoader {

    public <T> Set<T> load(Class<T> type) {

        ServiceLoader<T> loader = ServiceLoader.load(type);
        Set<T> instances = new HashSet<>();
        for (T instance : loader) {
            instances.add(instance);
        }
        return instances;
    }

}
