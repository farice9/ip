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
    /**
     * Main function
     */
    public static void main(String[] args) {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        // Load the storage file content
        try {
            DukeFiles.initializeFile(listOfTasks);
        } catch (IOException e) {
            Ui.printLoadingError();
        }
        Ui.greet();
        processCommand(listOfTasks);
        Ui.printGoodbye();
    }

    /**
     * Process the commands given by the user
     *
     * @param listOfTasks ArrayList containing list of tasks
     */
    public static void processCommand(ArrayList<Task> listOfTasks) {
        String command;
        String commandModified;
        boolean saidBye; // Logic flag to track if user said "bye"

        int taskCount; // Store the amount of tasks inserted

        // Repeatedly receive user command until "bye" is given
        do {
            // Collect user's command
            command = Ui.inputCommand();
            commandModified = command.trim().toLowerCase();

            // Checks if the command is "bye"
            saidBye = command.toLowerCase().trim().equals("bye");

            // Update taskCount value from class-level member in Task
            taskCount = Task.getNumberOfTasks();

            boolean isListModified = false;

            // Prints the list of tasks stored if "list" is called
            if (commandModified.equals("list")) {
                Ui.printList(listOfTasks);
            } else if (commandModified.contains("done")) {
                // Update done status for indicated task
                doneTask(command, listOfTasks);
                isListModified = true;
            } else if (commandModified.startsWith("delete")) {
                // Delete the indicated task
                deleteTask(listOfTasks, command);
                isListModified = true;
            } else if (!saidBye) {
                // Store the command into the array as a task if it's none of the above
                try {
                    addTask(command, listOfTasks, taskCount);
                    isListModified = true;
                } catch (InvalidCommandException e) {
                    // Informs user when command is inserted without stating the type of task
                    Ui.printInvalidTaskType();
                }
            }

            // Update the txt file if something is added/deleted/marked as done
            if (isListModified) {
                try {
                    DukeFiles.writeToFile(listOfTasks);
                } catch (IOException e) {
                    Ui.printWriteFileError();
                }
            }
        } while (!saidBye);
    }

    /**
     * Identifies the type of task given by user and add into the list
     *
     * @param command     user input at terminal
     * @param listOfTasks ArrayList containing tasks inserted by user
     * @param taskCount   Store the amount of tasks inserted
     * @throws InvalidCommandException exception due to commands without specifying the type
     */
    private static void addTask(String command, ArrayList<Task> listOfTasks, int taskCount) throws InvalidCommandException {
        String task;

        // Identifies the task type
        TaskType taskType = getTaskType(command);

        // Creates new object based on the type of the task
        switch (taskType) {
        case TODO:
            // Extract the string after "todo"
            task = command.trim().substring("todo".length()).trim();
            try {
                listOfTasks.add(new ToDo(task));
                listOfTasks.get(taskCount).printAddResult();
            } catch (InvalidCommandException e) {
                Ui.printEmptyTodoError();
            }
            break;
        case DEADLINE:
            // Command inserted by user will be processed and added into the list of tasks
            try {
                listOfTasks.add(new Deadline(command));
                listOfTasks.get(taskCount).printAddResult();
            } catch (InvalidCommandException e) {
                Ui.printEmptyDeadlineDescriptionError();
            } catch (InvalidDateException e) {
                Ui.printEmptyDeadlineDateError();
            }
            break;
        case EVENT:
            try {
                listOfTasks.add(new Event(command));
                listOfTasks.get(taskCount).printAddResult();
            } catch (InvalidCommandException e) {
                Ui.printEmptyEventDescriptionError();
            } catch (InvalidDateException e) {
                Ui.printEmptyEventDateError();
            }
            break;
        default:
            // Exception due to non-specific task type
            throw new InvalidCommandException();
        }
    }

    /**
     * Identifies and return the type of task the user has inserted
     *
     * @param command Command that the user input
     * @return the type of the task (event, deadline, todo)
     */
    private static TaskType getTaskType(String command) {
        TaskType taskType;

        String commandModified = command.trim().toLowerCase();

        // Checks the type of the task given
        if (commandModified.startsWith("todo")) {
            taskType = TaskType.TODO;
        } else if (commandModified.startsWith("deadline")) {
            taskType = TaskType.DEADLINE;
        } else if (commandModified.startsWith("event")) {
            taskType = TaskType.EVENT;
        } else {
            // taskType is NONE when user did not input specific type at the start
            taskType = TaskType.NONE;
        }
        return taskType;
    }

    /**
     * Deletes indicated task in the list
     *
     * @param listOfTasks ArrayList containing the list of task
     * @param command Command input by the user
     */
    private static void deleteTask(ArrayList<Task> listOfTasks, String command) {
        int taskCount = Task.getNumberOfTasks();
        int taskIndexPosition = command.toLowerCase().indexOf("delete") + "delete".length();

        // Check if there's an integer input after "delete"
        if (isIndexValid(command, taskIndexPosition)) {
            // Obtain the index of the task to be deleted
            int taskIndex = Integer.parseInt(command.substring(taskIndexPosition).trim()) - 1;

            // Perform the delete operation is the index is within range
            if ((taskIndex >= 0) && (taskIndex < taskCount)) {
                Task taskToBeRemoved = listOfTasks.get(taskIndex);

                listOfTasks.remove(taskIndex);
                Task.reduceNumberOfTasks();

                Ui.printDeleteResult(taskToBeRemoved);
            } else {
                Ui.printDeleteTaskNotFoundError();
            }
        } else {
            Ui.printIndexInputNotDetectedError();
        }
    }

    /**
     * Allows user to mark tasks as done
     *
     * @param command     The command input by user
     * @param listOfTasks Array containing tasks inserted by user
     */
    public static void doneTask(String command, ArrayList<Task> listOfTasks) {
        int taskCount = Task.getNumberOfTasks();
        int taskIndexPosition = command.toLowerCase().indexOf("done") + "done".length();

        if (isIndexValid(command, taskIndexPosition)) {
            // Extract the index number of the task to be marked as done
            int taskIndex = Integer.parseInt(command.substring(taskIndexPosition).trim()) - 1;

            // Make task as done if the task index inputted is at least 0 and less than the number of tasks inserted
            if ((taskIndex >= 0) && (taskIndex < taskCount)) {
                markAsDone(listOfTasks, taskIndex);
            } else {
                Ui.printInvalidDoneIndexError();
            }
        } else {
            Ui.printIndexInputNotDetectedError();
        }
    }

    /**
     * Mark the task in the list as done
     *
     * @param listOfTasks Array containing tasks inserted by user
     * @param taskIndex   Index of the task indicated
     */
    private static void markAsDone(ArrayList<Task> listOfTasks, int taskIndex) {
        // Inform the user if the task input has already been done
        if (listOfTasks.get(taskIndex).isDone) {
            Ui.printTaskAlreadyDoneMessage();
        } else {
            // Mark the task as done
            listOfTasks.get(taskIndex).isDone = true;
            Ui.printMarkAsDoneMessage(listOfTasks, taskIndex);
        }
    }

    /**
     * Checks if the "done" or "delete" command input by user is correct
     * It is correct if it does not have blank space and non-digits after "done" or "delete" input
     *
     * @param sentence String of command inserted by user
     * @return logic true if the command is valid
     */
    public static boolean isIndexValid(String sentence, int taskIndexPosition) {
        String stringAfterCommand;

        // Extract the string and convert it to array of characters
        stringAfterCommand = sentence.substring(taskIndexPosition).trim();

        char[] charAfterCommand = stringAfterCommand.toCharArray();

        // Return false if the substring after "done" only contains empty space
        if (stringAfterCommand.isEmpty()) {
            return false;
        }
        // Return false if the substring after "done" are not digits
        for (char character : charAfterCommand) {
            if (!Character.isDigit(character)) {
                return false;
            }
        }
        return true;
    }


}