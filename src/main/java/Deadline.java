/**
 * Subclass of Task, for tasks with deadline
 */
public class Deadline extends Task {
    protected String date;

    public Deadline(String description) throws InvalidCommandException, InvalidDateException {
        super(description);

        int dateStringIndex = description.indexOf("/");

        if (dateStringIndex < 0) {
            throw new InvalidDateException();
        } else {
            this.date = description.substring(dateStringIndex + "/by".length()).trim();
            numberOfTasks++;
            printAddResult();
        }
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + this.date + ")";
    }
}
