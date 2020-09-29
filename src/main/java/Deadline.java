import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * Subclass of Task, for tasks with deadline
 */
public class Deadline extends Task {
    protected String date;
    protected LocalDate localDate;
    protected String dateFormatted;
    protected String[] monthsHeaders = new String[] {"Jan", "Feb", "Mar"};

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

            formatDate();
            numberOfTasks++;
        }
    }

    public void formatDate() {
        try {
            // Parse date into LocalDate based on different formats
            if (Parser.startsWithMonth(date)) {
                // Occurs when parsing date in data file
                localDate = LocalDate.parse(this.date, DateTimeFormatter.ofPattern("MMM d yyyy"));
            } else {
                localDate = LocalDate.parse(this.date);
            }
            // To be printed in "MMM d yyyy" format later
            this.dateFormatted = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch(DateTimeParseException e) {
            // Print the raw date input if non-parsable format detected
            this.dateFormatted = this.date;
        }
    }

    @Override
    public String toString(){
        return Symbols.DEADLINE_INDICATOR + super.toString() + " (by: " + this.dateFormatted + ")";
    }
}
