package fetch.task.searcher;

import java.util.ArrayList;
import java.util.List;

import fetch.annotation.TestMethod;
import fetch.event.ProfilesReadEvent;
import fetch.event.ShowsSearchedEvent;
import fetch.event.core.EventDispatcher;
import fetch.event.handler.ProfilesReadEventHandler;
import fetch.exception.ConfigurationException;
import fetch.log.LogManager;
import fetch.log.Logger;
import fetch.plugin.PluginLoader;
import fetch.profile.Profile;
import fetch.task.Task;

public class ShowSearcher implements Task, ProfilesReadEventHandler {

    private final static Logger logger = LogManager.getLogger(ShowSearcher.class);

    private List<Profile> profiles = new ArrayList<>();
    private List<Entry> entries = new ArrayList<>();

    @Override
    public void execute() throws ConfigurationException {
        logger.info("Starting task " + getClass().getSimpleName());
        List<Crawler> crawlers = getCrawlers();
    }

    @TestMethod
    public List<Profile> getProfiles() {
        return profiles;
    }

    @TestMethod
    public List<Crawler> getCrawlers() {
        return PluginLoader.getInstance().getPlugins(Crawler.class);
    }

    @Override
    public int getOrder() {
        return 200;
    }

    @Override
    public void handle(ProfilesReadEvent event) {
        profiles = event.getProfiles();
    }

    @Override
    public void onFinish() {
        EventDispatcher.getInstance().dispatch((new ShowsSearchedEvent(entries)));
    }

}
