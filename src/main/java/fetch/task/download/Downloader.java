package fetch.task.download;

import java.util.Set;

import fetch.exception.ConfigurationException;
import fetch.plugin.PluginLoader;
import fetch.task.Task;

public class Downloader implements Task {

    @Override
    public void execute() throws ConfigurationException {
        RPCDownloader downloader = getDownloader();
    }

    public RPCDownloader getDownloader() {
        Set<RPCDownloader> downloaders = new PluginLoader().load(RPCDownloader.class);
        return downloaders.iterator().next();
    }

    @Override
    public int getOrder() {
        return 200;
    }

}
