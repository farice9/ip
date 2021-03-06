import java.time.LocalDate;

/**
 * Subclass of Task, represents events happening on certain time,
 * the date information is stored under this class
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

            this.dateFormatted = Parser.formatDate(date,localDate);
            numberOfTasks++;
        }
    }

    @Override
    public String toString(){
        return Symbols.EVENT_INDICATOR + super.toString() + " (at: " + this.dateFormatted + ")";
    }
}