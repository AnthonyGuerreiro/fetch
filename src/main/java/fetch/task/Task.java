package fetch.task;

import fetch.annotation.Listable;
import fetch.exception.ConfigurationException;
import fetch.plugin.HasOrder;

@Listable
public interface Task extends HasOrder {

    /**
     * Executes task.
     *
     * @throws ConfigurationException
     */
    void execute() throws ConfigurationException;

}
