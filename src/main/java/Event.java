import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Subclass of Task, for events happening on certain time
 */
public class Event extends Task {
    protected String date;
    protected LocalDate localDate;
    protected String dateFormatted;

    /**
     * Constructor for Event class type
     *
     * @param description command inserted by user
     * @throws InvalidCommandException Exception caused by empty command
     * @throws InvalidDateException Exception caused by absence of date input
     */
    public Event(String description) throws InvalidCommandException, InvalidDateException {
        super(description);

        // "/" indicates where the date input begins
        int dateStringIndex = description.indexOf("/");

        // Throws exception at the absence of date
        if (dateStringIndex < 0) {
            throw new InvalidDateException();
        } else {
            // Extract and separate the event details and date
            this.description = description.trim().substring("event".length(), dateStringIndex).trim();
            this.date = description.substring(dateStringIndex + "/at".length()).trim();

            formatDate();

            numberOfTasks++;
        }
    }

    public void formatDate() {
        try {
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
        return Symbols.EVENT_INDICATOR + super.toString() + " (at: " + this.dateFormatted + ")";
    }
}