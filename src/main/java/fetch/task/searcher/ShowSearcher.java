package fetch.task.searcher;

import java.util.List;

import fetch.exception.ConfigurationException;
import fetch.log.LogManager;
import fetch.log.Logger;
import fetch.plugin.PluginLoader;
import fetch.task.Task;

public class ShowSearcher implements Task {

    private final static Logger logger = LogManager.getLogger(ShowSearcher.class);

    @Override
    public void execute() throws ConfigurationException {
        logger.info("Starting task " + getClass().getSimpleName());
        List<Crawler> crawlers = getCrawlers();
    }

    public List<Crawler> getCrawlers() {
        return PluginLoader.getInstance().getPlugins(Crawler.class);
    }

    @Override
    public int getOrder() {
        return 200;
    }

}
