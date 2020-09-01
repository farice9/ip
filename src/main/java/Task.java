public class Task {
    protected String description;
    protected boolean isDone;

    /** Class-level member to store total number of task */
    private static int numberOfTasks = 0;

    //TODO: write something here
    public Task(){

    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numberOfTasks++;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public static int getNumberOfTasks(){
        return numberOfTasks;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public static void printNumberOfTasks(){
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
    }
}