import java.io.IOException;
import java.util.ArrayList;

/**
 * Duke is an interactive bot that helps user store their important
 * tasks in a comprehensive list that the user can modify
 *
 * Functions implemented:
 * 1) Adding tasks to a list
 * 2) Printing the list of tasks stored
 * 3) Indicating which task is done
 * 4) Different type of tasks (event, deadline, todo)
 * 5) Delete tasks
 * 6) Task storage onto a txt file
 * 7) Find tasks that corresponds given keyword
 * 8) Prints out user command guide
 */

public class Duke {
    /**
     * Initialize by loading the saved file content onto the ArrayList
     *
     * @param listOfTasks ArrayList containing list of tasks
     */
    public Duke(ArrayList<Task> listOfTasks) {
        // Load the storage file content
        try {
            DukeFiles.initializeFile(listOfTasks);
        } catch (IOException e) {
            Ui.printLoadingError();
        }
    }

    /**
     * Runs the entire process of the interactive bot,
     * receives and processes command by the user
     *
     * @param listOfTasks ArrayList containing list of tasks
     */
    public static void run(ArrayList<Task> listOfTasks) {
        Ui.printGreeting();
        boolean saidBye;
        // Repeatedly receive & process user command until "bye" is given
        do {
            // Collect user's command & identify the type
            String command = Ui.inputCommand();
            CommandType commandType = Parser.getCommandType(command);

            // Checks if the command is "bye"
            saidBye = (commandType == CommandType.BYE);

            Command.executeCommand(listOfTasks, command, commandType);
        } while (!saidBye);
        Ui.printGoodbye();
    }

    /**
     * Main function the program runs
     */
    public static void main(String[] args) {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        new Duke(listOfTasks);
        run(listOfTasks);
    }
}