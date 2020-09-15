/**
 * Subclass of Task, for tasks with deadline
 */
public class Deadline extends Task {
    protected String date;

    public Deadline(String description) throws InvalidCommandException, InvalidDateException {
        super(description);

        // "/" indicates where the date input begins
        int dateStringIndex = description.indexOf("/");

        if (dateStringIndex < 0) {
            throw new InvalidDateException();
        } else {
            // Extract the deadline details and date separated by "/"
            this.description = description.trim().substring("deadline".length(), dateStringIndex).trim();
            this.date = description.substring(dateStringIndex + "/by".length()).trim();

            numberOfTasks++;
        }
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + this.date + ")";
    }
}
