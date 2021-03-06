package fetch.task.filter;

import java.util.List;

import org.springframework.stereotype.Component;

import fetch.annotation.TestMethod;
import fetch.exception.ConfigurationException;
import fetch.log.LogManager;
import fetch.log.Logger;
import fetch.plugin.PluginLoader;
import fetch.task.Task;

@Component
public class ShowFilter implements Task {

    private final static Logger logger = LogManager.getLogger(ShowFilter.class);

    @Override
    public void execute() throws ConfigurationException {
        logger.info("tk.starting.task", getClass().getSimpleName());
        List<Filter> filters = getFilters();
    }

    @TestMethod
    public List<Filter> getFilters() {
        return PluginLoader.getInstance().getPlugins(Filter.class);
    }

    @Override
    public void onFinish() {
        logger.info("tk.finish.task", getClass().getSimpleName());
        // TODO implement
    }

    @Override
    public int getOrder() {
        return 400;
    }
}
