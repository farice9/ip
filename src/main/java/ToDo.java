/**
 * Subclass of Task, for basic tasks that needs to be done
 */
public class ToDo extends Task {

    public ToDo(String description){
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
