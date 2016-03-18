package fetch.task.filter;

import java.util.List;

import fetch.exception.ConfigurationException;
import fetch.log.LogManager;
import fetch.log.Logger;
import fetch.plugin.PluginLoader;
import fetch.task.Task;

public class ShowFilter implements Task {

    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(ShowFilter.class);
        logger.trace("test");
        logger.debug("test");

        logger.info("test");
        logger.warn("test");
        logger.error("test");
        logger.fatal("test");
    }

    @Override
    public void execute() throws ConfigurationException {
        List<Filter> filters = getFilters();
    }

    public List<Filter> getFilters() {
        return PluginLoader.getInstance().getPlugins(Filter.class);
    }

    @Override
    public int getOrder() {
        return 300;
    }
}
