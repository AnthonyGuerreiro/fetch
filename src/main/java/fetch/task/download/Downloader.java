package fetch.task.download;

import fetch.exception.ConfigurationException;
import fetch.plugin.PluginLoader;
import fetch.task.Task;

public class Downloader implements Task {

    @Override
    public void execute() throws ConfigurationException {
        RPCDownloader downloader = getDownloader();
    }

    public RPCDownloader getDownloader() throws ConfigurationException {
        return new PluginLoader().loadSingle(RPCDownloader.class);
    }

    @Override
    public int getOrder() {
        return 200;
    }

}
