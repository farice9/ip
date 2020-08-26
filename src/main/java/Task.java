public class Task {
    protected String description;
    protected boolean isDone;

    private static int numberOfTasks = 0;

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
}
