package fetch.task;

import fetch.exception.ConfigurationException;
import fetch.plugin.HasOrder;

public interface Task extends HasOrder {

    /**
     * Executes task.
     *
     * @throws ConfigurationException
     */
    void execute() throws ConfigurationException;

}
