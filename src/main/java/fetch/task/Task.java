package fetch.task;

import fetch.annotation.Indexable;
import fetch.exception.ConfigurationException;
import fetch.plugin.HasOrder;

@Indexable
public interface Task extends HasOrder {

    /**
     * Executes task.
     *
     * @throws ConfigurationException
     */
    void execute() throws ConfigurationException;

}
