import java.util.ArrayList;

/**
 * Contains a method to execute different functions of Duke
 */
public class Command {
    /**
     * Runs different functions of Duke based on the command type
     * and updates the data file if the array list is updated
     *
     * @param listOfTasks ArrayList containing list of tasks
     * @param command Raw command input from the user
     * @param commandType Type of the command
     */
    public static void executeCommand(ArrayList<Task> listOfTasks, String command, CommandType commandType) {
        boolean isListModified = (commandType == CommandType.DONE || commandType == CommandType.DELETE
                || commandType == CommandType.TASK);

        if (commandType == CommandType.LIST) {
            Ui.printList(listOfTasks);
        } else if (commandType == CommandType.DONE) {
            // Update done status for indicated task
            TaskList.doneTask(command, listOfTasks);
        } else if (commandType == CommandType.DELETE) {
            TaskList.deleteTask(listOfTasks, command);
        } else if (commandType == CommandType.TASK) {
            try {
                TaskList.addTask(command, listOfTasks);
            } catch (InvalidCommandException e) {
                // Informs user when command is inserted without stating the type of task
                Ui.printInvalidTaskType();
            }
        }
        // Updates the tasks.txt file if changes occur to the array list
        DukeFiles.updateFile(listOfTasks, isListModified);
    }
}