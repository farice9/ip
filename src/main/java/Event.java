/**
 * Subclass of Task, for events happening on certain time
 */
public class Event extends Task {
    protected String date;

    public Event(String description, String at){
        super(description);
        this.date = date;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + this.date + ")";
    }
}