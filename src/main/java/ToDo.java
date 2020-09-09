/**
 * Subclass of Task, for basic tasks that needs to be done
 */
public class ToDo extends Task {

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
