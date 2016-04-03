package fetch.test.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import fetch.plugin.PluginLoader;
import fetch.task.Task;
import fetch.task.download.Downloader;
import fetch.task.filter.ShowFilter;
import fetch.task.reader.Reader;
import fetch.task.searcher.ShowSearcher;
import fetch.task.splitter.EpisodeSplitter;
import fetch.test.AbstractTest;

public class TestTask extends AbstractTest {

    private List<Task> getTasks() {
        return PluginLoader.getInstance().getTasks();
    }

    @Test
    public void testLoadTasks() {
        List<Task> tasks = getTasks();
        assertEquals(5, tasks.size());
        assertTrue(tasks.get(0) instanceof Reader);
        assertTrue(tasks.get(1) instanceof ShowSearcher);
        assertTrue(tasks.get(2) instanceof EpisodeSplitter);
        assertTrue(tasks.get(3) instanceof ShowFilter);
        assertTrue(tasks.get(4) instanceof Downloader);
    }
}
