package fetch.task.searcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fetch.annotation.TestMethod;
import fetch.event.ProfilesReadEvent;
import fetch.event.ShowsSearchedEvent;
import fetch.event.core.EventDispatcher;
import fetch.event.handler.ProfilesReadEventHandler;
import fetch.exception.ConfigurationException;
import fetch.exception.FetchException;
import fetch.log.LogManager;
import fetch.log.Logger;
import fetch.plugin.PluginLoader;
import fetch.profile.Profile;
import fetch.profile.Show;
import fetch.task.Task;

public class ShowSearcher implements Task, ProfilesReadEventHandler {

    private final static Logger logger = LogManager.getLogger(ShowSearcher.class);

    private List<Profile> profiles = new ArrayList<>();
    private Map<Show, List<Entry>> entries = new HashMap<>();

    @Override
    public void execute() throws ConfigurationException {
        logger.info("tk.starting.task", getClass().getSimpleName());
        List<Crawler> crawlers = getCrawlers();
        List<Show> shows = getShows();
        crawl(shows, crawlers);
    }

    private void crawl(List<Show> shows, List<Crawler> crawlers) {
        if (shows.isEmpty()) {
            logger.info("sc.no.shows");
        }
        if (crawlers.isEmpty()) {
            logger.info("sc.no.crawlers");
        }

        // TODO get url from config
        String template = "http://www.torrentz.com/search?q=";
        for (Crawler crawler : crawlers) {
            for (Show show : shows) {
                try {
                    String url = template.replace("{show}", show.getName());
                    List<Entry> entries = crawler.getEntries(url);
                    this.entries.put(show, entries);
                } catch (FetchException e) {
                    // exception already logged in crawler
                }
            }
        }
    }

    private List<Show> getShows() {
        return profiles.stream().map(profile -> profile.getShows())
                .flatMap(showList -> showList.stream()).collect(Collectors.toList());
    }

    @TestMethod
    public List<Profile> getProfiles() {
        return profiles;
    }

    @TestMethod
    public Map<Show, List<Entry>> getEntries() {
        return entries;
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
        logger.info("tk.finish.task", getClass().getSimpleName());
        EventDispatcher.getInstance().dispatch((new ShowsSearchedEvent(entries)));
    }

}
