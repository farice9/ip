import java.time.LocalDate;

/**
 * Subclass of Task, represents tasks with deadline,
 * a date information is stored under this class
 */
public class Deadline extends Task {
    protected String date;
    protected LocalDate localDate;
    protected String dateFormatted;

    /**
     * Builds a new Deadline task
     *
     * @param description Description of the deadline
     * @throws InvalidCommandException Exception caused by empty description
     * @throws InvalidDateException Exception caused by absent date
     */
    public Deadline(String description) throws InvalidCommandException, InvalidDateException {
        super(description);

        // "/" indicates where the date input begins
        int dateStringIndex = description.indexOf("/");

        // Throws exception at the absence of date
        if (dateStringIndex < 0) {
            throw new InvalidDateException();
        } else {
            // Extract the deadline details and date separated by "/"
            this.description = description.trim().substring("deadline".length(), dateStringIndex).trim();
            this.date = description.substring(dateStringIndex + "/by".length()).trim();

            this.dateFormatted = Parser.formatDate(date, localDate);
            numberOfTasks++;
        }
    }

    @Override
    public String toString(){
        return Symbols.DEADLINE_INDICATOR + super.toString() + " (by: " + this.dateFormatted + ")";
    }
}
