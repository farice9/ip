import java.util.ArrayList;

public class Command {
    public static void executeCommand(ArrayList<Task> listOfTasks, String command, CommandType commandType) {
        boolean isListModified = (commandType == CommandType.DONE || commandType == CommandType.DELETE
                || commandType == CommandType.TASK);

        // Prints the list of tasks stored if "list" is called
        if (commandType == CommandType.LIST) {
            Ui.printList(listOfTasks);
        } else if (commandType == CommandType.DONE) {
            // Update done status for indicated task
            TaskList.doneTask(command, listOfTasks);
        } else if (commandType == CommandType.DELETE) {
            // Delete the indicated task
            TaskList.deleteTask(listOfTasks, command);
        } else if (commandType == CommandType.TASK) {
            // Store the command into the ArrayList as a task if it's none of the above
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