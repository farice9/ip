/**
 * Subclass of Task, for events happening on certain time
 */
public class Event extends Task {
    protected String date;

    public Event(String description) throws InvalidCommandException, InvalidDateException {
        super(description);

        int dateStringIndex = description.indexOf("/");

        if (dateStringIndex < 0) {
            throw new InvalidDateException();
        } else {
            this.date = description.substring(dateStringIndex + "/at".length()).trim();
            numberOfTasks++;
            printAddResult();
        }
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + this.date + ")";
    }
}