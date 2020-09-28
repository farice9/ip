/**
 * The Task class contains different attributes of a task stored by user
 *
 * Attributes : description of the task, is the task done
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /** Class-level member to store total number of task */
    protected static int numberOfTasks = 0;

    /**
     * Constructor for Task class
     *
     * @param description description of the task inserted by the user
     */
    public Task(String description) throws InvalidCommandException {
        if (description.isEmpty()) {
            throw new InvalidCommandException();
        } else {
            this.description = description;
            this.isDone = false;
        }
    }

    /**
     * Informs the user the details of the task they added (if successful)
     */
    protected void printAddResult() {
        Ui.printDivider();
        System.out.println("Alrighty! I've added the following task:");
        System.out.println(this);
        printNumberOfTasks(); // Inform user how many tasks they have
        Ui.printDivider();
    }

    /**
     * To obtain the boxed status icon to be printed
     *
     * @return the boxed status icon of the task (tick / cross)
     */
    public String getStatusIcon() {
        return (isDone ? Symbols.BOXED_TICK : Symbols.BOXED_CROSS); //return boxed tick or cross symbols
    }

    /**
     * To obtain the number of tasks created
     *
     * @return the number of tasks created
     */
    public static int getNumberOfTasks(){
        return numberOfTasks;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }

    /**
     * Prints the number of tasks
     */
    public static void printNumberOfTasks(){
        System.out.println("Now you have " + numberOfTasks + " task(s) in the list.");
    }

    public static void reduceNumberOfTasks() {
        numberOfTasks--;
    }

    public void doneTask() {
        this.isDone = true;
    }
}