package fetch.task.splitter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fetch.annotation.TestMethod;
import fetch.event.ShowsSearchedEvent;
import fetch.event.handler.ShowsSearchedEventHandler;
import fetch.exception.ConfigurationException;
import fetch.log.LogManager;
import fetch.log.Logger;
import fetch.task.Task;
import fetch.task.searcher.Entry;

public class EpisodeSplitter implements Task, ShowsSearchedEventHandler {

    private final static Logger logger = LogManager.getLogger(EpisodeSplitter.class);
    private List<Entry> entries = new ArrayList<>();
    private Set<List<Entry>> splittedEntries = new HashSet<>();

    @Override
    public void execute() throws ConfigurationException {
        logger.info("tk.starting.task", getClass().getSimpleName());
        if (entries.isEmpty()) {
            logger.info("es.no.shows");
            return;
        }

        splitEntries();
    }

    private void splitEntries() {
        // TODO implement
        splittedEntries.add(entries);
    }

    @Override
    public void onFinish() {
        // TODO Auto-generated method stub

    }

    @TestMethod
    public Set<List<Entry>> getSplittedEntries() {
        return splittedEntries;
    }

    @Override
    public void handle(ShowsSearchedEvent event) {
        entries = event.getEntries();
    }

    @Override
    public int getOrder() {
        return 300;
    }

}
