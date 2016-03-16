package fetch.test.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import fetch.plugin.PluginLoader;
import fetch.task.Task;
import fetch.task.download.Downloader;
import fetch.task.reader.Reader;
import fetch.task.searcher.ShowSearcher;

public class TestTask {

    private List<Task> getTasks() {
        return new PluginLoader().load(Task.class);
    }

    @Test
    public void testLoadTasks() {
        List<Task> tasks = getTasks();
        assertEquals(3, tasks.size());
        assertTrue(tasks.get(0) instanceof Reader);
        assertTrue(tasks.get(1) instanceof ShowSearcher);
        assertTrue(tasks.get(2) instanceof Downloader);
    }
}
