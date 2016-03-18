package fetch.task.searcher;

import java.util.List;

import fetch.exception.ConfigurationException;
import fetch.plugin.PluginLoader;
import fetch.task.Task;

public class ShowSearcher implements Task {

    @Override
    public void execute() throws ConfigurationException {
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
