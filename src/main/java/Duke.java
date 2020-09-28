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

    public Duke(ArrayList<Task> listOfTasks) {
        // Load the storage file content
        try {
            DukeFiles.initializeFile(listOfTasks);
        } catch (IOException e) {
            Ui.printLoadingError();
        }
    }

    /**
     * Process the commands given by the user
     *
     * @param listOfTasks ArrayList containing list of tasks
     */
    public static void run(ArrayList<Task> listOfTasks) {
        Ui.printGreeting();
        boolean saidBye;
        // Repeatedly receive user command until "bye" is given
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
     * Main function
     */
    public static void main(String[] args) {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        new Duke(listOfTasks);
        run(listOfTasks);
    }
}