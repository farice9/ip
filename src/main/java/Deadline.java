/**
 * Subclass of Task, for tasks with deadline
 */
public class Deadline extends Task {
    protected String date;

    public Deadline(String description, String date) throws InvalidCommandException, InvalidDateException {
        super(description);
        System.out.println(date);
        if (date.isEmpty()){
            throw new InvalidDateException();
        } else {
            this.date = date;
            printAddResult();
        }
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + this.date + ")";
    }
}
