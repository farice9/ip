/**
 * Subclass of Task, represents basic tasks that needs to be done
 */
public class ToDo extends Task {

    /**
     * Constructor class for ToDo class
     *
     * @param description command inserted by the user after "todo" input
     * @throws InvalidCommandException Exception caused by empty command
     */
    public ToDo(String description) throws InvalidCommandException {
        super(description);
        numberOfTasks++;
    }

    @Override
    public String toString() {
        return Symbols.TODO_INDICATOR + super.toString();
    }
}
