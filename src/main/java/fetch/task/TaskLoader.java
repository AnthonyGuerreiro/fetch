package fetch.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import fetch.plugin.PluginLoader;

public class TaskLoader {

    public List<Task> getTasks() {
        Set<Task> tasks = new PluginLoader().load(Task.class);
        return sort(tasks);
    }

    private List<Task> sort(Set<Task> tasks) {
        List<Task> sortedTasks = new ArrayList<>(tasks.size());
        sortedTasks.addAll(tasks);
        sortedTasks.sort((t1, t2) -> t1.getOrder() - t2.getOrder());
        return sortedTasks;
    }

}
