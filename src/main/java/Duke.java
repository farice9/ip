import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * An interactive bot that performs various tasks based on user command
 * <p>
 * Last updated : 28 September 2020
 * <p>
 * Functions implemented:
 * 1) Adding tasks to a list
 * 2) Printing the list of tasks stored
 * 3) Indicating which task is done
 * 4) Different type of tasks (event, deadline, todo)
 * 5) Delete tasks
 * 6) Task storage onto a txt file
 */

public class Duke {
    public static void Duke() {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        // Load the storage file content
        try {
            DukeFiles.initializeFile(listOfTasks);
        } catch (IOException e) {
            Ui.printLoadingError();
        }
    }


    /**
     * Main function
     */
    public static void main(String[] args) {
        Duke();

        run(listOfTasks);

    }


    /**
     * Process the commands given by the user
     *
     * @param listOfTasks ArrayList containing list of tasks
     */
    public static void run(ArrayList<Task> listOfTasks) {
        Ui.printGreeting();

        // Repeatedly receive user command until "bye" is given
        do {
            // Collect user's command
            String command = Ui.inputCommand();

            CommandType commandType = Parser.getCommandType(command);

            boolean isListModified = (commandType == CommandType.DONE || commandType == CommandType.DELETE
                    || commandType == CommandType.TASK);

            // Checks if the command is "bye"
            boolean saidBye = (commandType == CommandType.BYE);

            // Prints the list of tasks stored if "list" is called
            if (commandType == CommandType.LIST) {
                Ui.printList(listOfTasks);
            } else if (commandType == CommandType.DONE) {
                // Update done status for indicated task
                TaskList.doneTask(command, listOfTasks);
                isListModified = true;
            } else if (commandType == CommandType.DELETE) {
                // Delete the indicated task
                TaskList.deleteTask(listOfTasks, command);
                isListModified = true;
            } else if (!saidBye) {
                // Store the command into the array as a task if it's none of the above
                try {
                    TaskList.addTask(command, listOfTasks);
                    isListModified = true;
                } catch (InvalidCommandException e) {
                    // Informs user when command is inserted without stating the type of task
                    Ui.printInvalidTaskType();
                }
            }
            // Updates the tasks.txt file if changes occur to the array list
            DukeFiles.updateFile(listOfTasks, isListModified);
        } while (!saidBye);

        Ui.printGoodbye();
    }


}