package fetch.test.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Test;

import fetch.exception.ConfigurationException;
import fetch.plugin.Plugin;
import fetch.plugin.PluginLoader;
import fetch.task.Task;
import fetch.task.reader.Reader;
import fetch.task.searcher.ShowSearcher;

public class TestEvents {

    private Optional<Task> getTask(Class<? extends Plugin> klass) {
        List<Task> tasks = PluginLoader.getInstance().getPlugins(Task.class);
        return tasks.stream().filter(task -> task.getClass().equals(klass)).findFirst();
    }

    @Test
    public void testProfilesReadEventHandler() throws ConfigurationException {
        Reader reader = (Reader) getTask(Reader.class).get();
        ShowSearcher showSearcher = (ShowSearcher) getTask(ShowSearcher.class).get();

        assertTrue(reader.getProfiles().isEmpty());
        assertTrue(showSearcher.getProfiles().isEmpty());
        reader.execute();
        assertFalse(reader.getProfiles().isEmpty());
        assertTrue(showSearcher.getProfiles().isEmpty());
        reader.onFinish();
        assertEquals(reader.getProfiles(), showSearcher.getProfiles());
    }
}
