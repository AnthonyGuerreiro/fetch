package fetch.plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fetch.task.Task;

public class PluginLoader {

    private static PluginLoader instance = new PluginLoader();

    private ApplicationContext context = new ClassPathXmlApplicationContext(
            "/appContext.xml");

    private PluginLoader() {
    }

    public static PluginLoader getInstance() {
        return instance;
    }

    public List<Task> getTasks() {
        return getPlugins(Task.class);
    }

    public <T> T getPlugin(String name, Class<T> type) {
        return context.getBean(name, type);
    }

    public <T> T getPlugin(Class<T> type) {
        return context.getBean(type);
    }

    public <T> List<T> getPlugins(Class<T> type) {
        Map<String, T> beansOfType = context.getBeansOfType(type);
        List<T> plugins = new ArrayList<>(beansOfType.values());
        if (HasOrder.class.isAssignableFrom(type)) {
            sort(plugins);
        }
        return plugins;
    }

    private <T> void sort(List<T> instances) {
        List<HasOrder> list = instances.stream().map(instance -> (HasOrder) instance)
                .collect(Collectors.toList());
        list.sort((t1, t2) -> t1.getOrder() - t2.getOrder());
        instances.clear();
        list.forEach(t -> instances.add((T) t));
    }
}
