package fetch.test.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import fetch.task.Task;
import fetch.task.TaskLoader;
import fetch.task.download.Downloader;
import fetch.task.reader.Reader;

public class TestTask {

    @Test
    public void testLoadTasks() {
        List<Task> tasks = new TaskLoader().getTasks();
        assertEquals(2, tasks.size());
        assertTrue(tasks.get(0) instanceof Reader);
        assertTrue(tasks.get(1) instanceof Downloader);
    }
}
