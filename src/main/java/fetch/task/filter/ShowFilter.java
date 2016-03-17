package fetch.task.filter;

import java.util.List;

import fetch.exception.ConfigurationException;
import fetch.plugin.PluginLoader;
import fetch.task.Task;

public class ShowFilter implements Task {

    @Override
    public void execute() throws ConfigurationException {
        List<Filter> filters = getFilters();
    }

    public List<Filter> getFilters() {
        return new PluginLoader().load(Filter.class);
    }

    @Override
    public int getOrder() {
        return 300;
    }
}
