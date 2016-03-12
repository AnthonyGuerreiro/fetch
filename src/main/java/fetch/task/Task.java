package fetch.task;

import fetch.exception.ConfigurationException;

public interface Task {

    /**
     * Executes task.
     * 
     * @throws ConfigurationException
     */
    void execute() throws ConfigurationException;

    /**
     * Returns order. <br />
     * Order is used to specify when this task will run compared to other tasks.
     * <br />
     * The order returned should be equal or greater than 0.<br />
     * If two tasks have the same order, it is not certain which task will run
     * first.
     *
     * @return order.
     */
    int getOrder();
}
