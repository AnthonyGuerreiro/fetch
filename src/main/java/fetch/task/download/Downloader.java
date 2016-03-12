package fetch.task.download;

import fetch.exception.ConfigurationException;
import fetch.task.Task;

public class Downloader implements Task {

    @Override
    public void execute() throws ConfigurationException {
        // TODO Auto-generated method stub

    }

    @Override
    public int getOrder() {
        return 200;
    }

}
