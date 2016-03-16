package fetch.plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import fetch.exception.ConfigurationException;

public class PluginLoader {

    public <T> List<T> load(Class<T> type) {

        ServiceLoader<T> loader = ServiceLoader.load(type);
        List<T> instances = new ArrayList<>();
        for (T instance : loader) {
            instances.add(instance);
        }
        if (HasOrder.class.isAssignableFrom(type)) {
            sort((List<HasOrder>) instances);
        }
        return instances;
    }

    private void sort(List<? extends HasOrder> instances) {
        instances.sort((t1, t2) -> t1.getOrder() - t2.getOrder());
    }

    public <T> T loadSingle(Class<T> type) throws ConfigurationException {

        List<T> instances = load(type);
        if (instances.size() == 1) {
            return instances.iterator().next();
        }

        String msg = "Expected 1 plugin of type " + type.getCanonicalName() + ". Found <"
                + instances.size() + ">.";
        throw new ConfigurationException(msg);
    }

}
