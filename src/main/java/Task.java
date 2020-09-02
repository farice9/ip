/**
 * The Task class contains different attributes of a task stored by user
 *
 * Attributes : description of the task, is the task done
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /** Class-level member to store total number of task */
    private static int numberOfTasks = 0;

    /**
     * Constructor for Task class
     *
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numberOfTasks++;
    }

    /**
     * To obtain the status icon to be printed
     *
     * @return the status icon of the task (tick / cross)
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
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
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Prints the number of tasks
     */
    public static void printNumberOfTasks(){
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
    }
}