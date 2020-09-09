package tasks;

import exceptions.InvalidCommandException;

/**
 * Subclass of tasks.Task, for basic tasks that needs to be done
 */
public class ToDo extends Task {

    /**
     * Constructor class for tasks.ToDo class
     *
     * @param description command inserted by the user after "todo" input
     * @throws InvalidCommandException Exception caused by empty command
     */
    public ToDo(String description) throws InvalidCommandException {
        super(description);
        numberOfTasks++;
        printAddResult();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
