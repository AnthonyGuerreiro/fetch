package fetch.core;

import java.util.List;

import fetch.exception.ConfigurationException;
import fetch.log.LogManager;
import fetch.log.Logger;
import fetch.plugin.PluginLoader;
import fetch.task.Task;

public class Fetch {

    private final static Logger logger = LogManager.getLogger(Fetch.class);

    public static void main(String[] args) throws ConfigurationException {
        new Fetch().start(args);
    }

    private List<Task> getTasks() {
        return PluginLoader.getInstance().getTasks();
    }

    private void start(String[] args) throws ConfigurationException {
        List<Task> tasks = getTasks();

        logger.info("Executing tasks");
        for (Task task : tasks) {
            task.execute();
            task.onFinish();
        }
    }
}
