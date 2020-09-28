import java.util.ArrayList;
import java.util.Scanner;

/**
 * Contains all the methods needed for user to interact with Duke
 *
 * Helps receive command input from the user and handles all the printing of messages
 */
public class Ui {

    /**
     * Receive command input from the user via terminal
     *
     * @return the command input as a String
     */
    public static String inputCommand() {
        String command;
        Scanner in = new Scanner(System.in);

        command = in.nextLine();

        return command;
    }

    /**
     * Greets the user
     */
    public static void printGreeting() {
        printDivider();
        System.out.println(Message.GREET);
        printUserGuide();
        printDivider();
    }

    /**
     * Prints a simple user command guide
     */
    public static void printUserGuide() {
        System.out.println(Message.USER_COMMAND_GUIDE);
        System.out.println("Go ahead!");
    }

    /**
     * Prints the standard length divider
     */
    public static void printDivider() {
        System.out.println(Message.LINE_DIVIDER);
    }

    /**
     * Prints out what the bot says with divider on top and bottom
     *
     * @param sentence String to be printed
     */
    public static void botSpeak(String sentence) {
        printDivider();
        System.out.println(sentence);
        printDivider();
    }

    /**
     * Prints the goodbye message
     */
    public static void printGoodbye() {
        botSpeak(Message.GOODBYE);
    }

    /**
     * Informs user when command is inserted without stating the type of task
     */
    public static void printInvalidTaskType() {
        botSpeak(Message.INVALID_TASK_TYPE_ERROR);
    }

    /**
     * Informs user about error with writing the file
     */
    public static void printWriteFileError() {
        System.out.println(Message.WRITE_FILE_ERROR);
    }

    /**
     * Informs user when todo task is called without description
     */
    public static void printEmptyTodoError() {
        botSpeak(Message.EMPTY_TODO_ERROR);
    }

    /**
     * Informs user when deadline task is called without description
     */
    public static void printEmptyDeadlineDescriptionError() {
        botSpeak(Message.EMPTY_DEADLINE_DESCRIPTION_ERROR);
    }

    /**
     * Informs user when deadline task is called without date
     */
    public static void printEmptyDeadlineDateError() {
        botSpeak(Message.EMPTY_DEADLINE_DATE_ERROR);
    }

    public static void printEmptyEventDescriptionError() {
        botSpeak(Message.EMPTY_EVENT_DESCRIPTION_ERROR);
    }
    public static void printEmptyEventDateError() {
        botSpeak(Message.EMPTY_EVENT_DATE_ERROR);
    }

    /**
     * Prints messages involved when performing "delete" function
     */
    public static void printDeleteTaskNotFoundError() {
        botSpeak(Message.DELETE_TASK_NOT_FOUND_ERROR);
    }

    /**
     * Prints the result of deleting task when successful
     *
     * @param taskToBeRemoved Task in the array list to be removed
     */
    public static void printDeleteResult(Task taskToBeRemoved) {
        printDivider();
        System.out.println("Noted! I have removed the task requested:");
        System.out.println(taskToBeRemoved);
        Task.printNumberOfTasks();
        printDivider();
    }

    /**
     * Informs user when the "done" task index input is invalid
     */
    public static void printInvalidDoneIndexError() {
        botSpeak(Message.INVALID_DONE_INDEX_ERROR);
    }

    /**
     * Informs user when the task indicated to be marked done has already been done
     */
    public static void printTaskAlreadyDoneMessage() {
        botSpeak(Message.TASK_ALREADY_DONE_MESSAGE);
    }

    /**
     * Informs user that a task is marked as done
     */
    public static void printMarkAsDoneMessage(ArrayList<Task> listOfTasks, int taskIndex) {
        botSpeak(Message.MARK_AS_DONE_MESSAGE + "\n"
                + listOfTasks.get(taskIndex));
    }

    /**
     * Informs user when index input is not detected
     */
    public static void printIndexInputNotDetectedError() {
        botSpeak(Message.INDEX_INPUT_NOT_DETECTED_ERROR);
    }

    /**
     * Informs user the list is empty when user calls "list" function
     */
    public static void printEmptyTaskListMessage() {
        botSpeak(Message.EMPTY_TASK_LIST_MESSAGE);
    }

    /**
     * Prints out the list of tasks stored when "list" command is called
     *
     * @param listOfTasks Array containing tasks inserted by user
     */
    public static void printList(ArrayList<Task> listOfTasks) {
        int taskCount = Task.getNumberOfTasks();

        // Notify the user if no tasks has been added yet
        if (taskCount == 0) {
            printEmptyTaskListMessage();
        } else {
            // Prints out the list of commands with respective index number
            printDivider();
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + "." + listOfTasks.get(i));
            }
            printDivider();
        }
    }



    /**
     * Informs user the file is being loaded onto the array list
     */
    public static void printLoadingFile() {
        System.out.println(Message.LOADING_FILE);
    }

    /**
     * Informs user when the loading of data file is done
     */
    public static void printDoneLoading() {
        System.out.println(Message.DONE_LOADING);
    }

    /**
     * Informs user when there's a problem identifying the task when reading from the data file
     */
    public static void printReadFileIdentificationError() {
        System.out.println(Message.READ_FILE_IDENTIFICATION_ERROR);
    }

    /**
     * Informs user about problems regarding loading the data file
     */
    public static void printLoadingError() {
        System.out.println(Message.LOADING_ERROR);
    }

    /**
     * Informs user when there's an error with adding the task from data file
     */
    public static void printReadFileAddTaskError() {
        System.out.println(Message.READ_FILE_ADD_TASK_ERROR);
    }

    /**
     * Informs user when there's error adding task caused by invalid date input
     */
    public static void printReadFileDateError() {
        System.out.println(Message.READ_FILE_DATE_ERROR);
    }

    /**
     * Informs user when the data file is not detected and will be created
     */
    public static void printCreateNewFile() {
        System.out.println(Message.CREATE_NEW_FILE);
    }

}
