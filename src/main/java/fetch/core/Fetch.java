package fetch.core;

import java.util.List;

import fetch.exception.ConfigurationException;
import fetch.plugin.PluginLoader;
import fetch.task.Task;

public class Fetch {

    public static void main(String[] args) throws ConfigurationException {
        new Fetch().start(args);
    }

    private List<Task> getTasks() {
        return PluginLoader.getInstance().getPlugins(Task.class);
    }

    private void start(String[] args) throws ConfigurationException {
        List<Task> tasks = getTasks();
        for (Task task : tasks) {
            task.execute();
            task.onFinish();
        }
    }
}
