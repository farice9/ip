import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    public static String inputCommand() {
        String command;
        Scanner in = new Scanner(System.in);

        command = in.nextLine();

        return command;
    }

    public static void printLoadingError() {
        System.out.println(Message.LOADING_ERROR);
    }

    public static void greet() {
        printDivider();
        System.out.println(Message.GREET);
        printUserGuide();
        printDivider();
    }

    public static void printUserGuide() {
        System.out.println(Message.USER_COMMAND_GUIDE);
        System.out.println("Go ahead!");
    }

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

    public static void printGoodbye() {
        botSpeak(Message.GOODBYE);
    }

    // Informs user when command is inserted without stating the type of task
    public static void printInvalidTaskType() {
        botSpeak(Message.INVALID_TASK_TYPE_ERROR);
    }

    public static void printWriteFileError() {
        System.out.println(Message.WRITE_FILE_ERROR);
    }

    public static void printEmptyTodoError() {
        botSpeak(Message.EMPTY_TODO_ERROR);
    }
    public static void printEmptyDeadlineDescriptionError() {
        botSpeak(Message.EMPTY_DEADLINE_DESCRIPTION_ERROR);
    }
    public static void printEmptyDeadlineDateError() {
        botSpeak(Message.EMPTY_DEADLINE_DATE_ERROR);
    }

    public static void printEmptyEventDescriptionError() {
        botSpeak(Message.EMPTY_EVENT_DESCRIPTION_ERROR);
    }
    public static void printEmptyEventDateError() {
        botSpeak(Message.EMPTY_EVENT_DATE_ERROR);
    }

    public static void printDeleteTaskNotFoundError() {
        botSpeak(Message.DELETE_TASK_NOT_FOUND_ERROR);
    }

    public static void printIndexInputNotDetectedError() {
        botSpeak(Message.INDEX_INPUT_NOT_DETECTED_ERROR);
    }

    public static void printDeleteResult(Task taskToBeRemoved) {
        printDivider();
        System.out.println("Noted! I have removed the task requested:");
        System.out.println(taskToBeRemoved);
        Task.printNumberOfTasks();
        printDivider();
    }

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

    public static void printInvalidDoneIndexError() {
        botSpeak(Message.INVALID_DONE_INDEX_ERROR);
    }

    public static void printTaskAlreadyDoneMessage() {
        botSpeak(Message.TASK_ALREADY_DONE_MESSAGE);
    }

    public static void printMarkAsDoneMessage(ArrayList<Task> listOfTasks, int taskIndex) {
        botSpeak(Message.MARK_AS_DONE_MESSAGE + "\n"
                + listOfTasks.get(taskIndex));
    }

    public static void printLoadingFile() {
        System.out.println(Message.LOADING_FILE);
    }

    public static void printDoneLoading() {
        System.out.println(Message.DONE_LOADING);
    }

    public static void printReadFileIdentificationError() {
        System.out.println(Message.READ_FILE_IDENTIFICATION_ERROR);
    }

    public static void printReadFileAddTaskError() {
        System.out.println(Message.READ_FILE_ADD_TASK_ERROR);
    }

    public static void printReadFileDateError() {
        System.out.println(Message.READ_FILE_DATE_ERROR);
    }


}
