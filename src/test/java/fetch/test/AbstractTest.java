package fetch.test;

import java.util.List;
import java.util.Optional;

import fetch.plugin.PluginLoader;
import fetch.profile.Profile;
import fetch.profile.Show;
import fetch.task.Task;
import fetch.task.searcher.Entry;

public class AbstractTest {

    protected <T extends Task> Optional<T> getTask(Class<T> klass) {

        List<Task> tasks = PluginLoader.getInstance().getTasks();
        return tasks.stream().filter(task -> task.getClass().equals(klass))
                .map(task -> (T) task).findFirst();
    }

    protected Profile getProfile(String name) {
        Profile profile = new Profile();
        profile.setName(name);
        return profile;
    }

    protected Show getShow(String name, Profile parent) {
        Show show = new Show();
        show.setName(name);
        show.setParent(parent);
        return show;
    }

    protected Entry getEntry(String value) {
        return new TestEntryImpl(value);
    }
}
