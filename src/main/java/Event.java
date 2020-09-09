/**
 * Subclass of Task, for events happening on certain time
 */
public class Event extends Task {
    protected String date;

    public Event(String description, String date) throws InvalidCommandException, InvalidDateException {
        super(description);
        if (date.isEmpty()){
            throw new InvalidDateException();
        } else {
            this.date = date;
            printAddResult();
        }
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + this.date + ")";
    }
}