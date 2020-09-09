import java.util.Scanner;
import java.util.Arrays;

/**
 * An interactive bot that performs various tasks based on user command
 * <p>
 * Last updated : 2 September 2020
 * <p>
 * Functions implemented:
 * 1) Adding tasks to a list
 * 2) Printing the list of tasks stored
 * 3) Indicating which task is done
 * 4) Different type of tasks (event, deadline, todo)
 */

public class Duke {
    /**
     * Main function
     */
    public static void main(String[] args) {
        greet();
        processCommand();
        exit();
    }

    /**
     * Prints greet user message
     */
    public static void greet() {
        botSpeak("Hey mate! Nice to meet you, I'm Duke!\nHow can I help you?");
    }

    /**
     * Prints goodbye message
     */
    public static void exit() {
        botSpeak("Goodbye & have a nice day! Hope to see you again!");
    }

    /**
     * Process the commands given by the user
     * Possible commands : list, bye, done (_digit_), (any string)
     */
    public static void processCommand() {
        String command;
        boolean saidBye; // Logic flag to track if user said "bye"

        // Stores the commands given
        Task[] listOfTasks = new Task[100]; // Can store 100 tasks
        int taskCount; // Store the amount of tasks inserted

        // Repeatedly receive user command until "bye" is given
        do {
            // Collect user's command
            command = inputCommand();


            // Checks if the command is "bye"
            saidBye = command.toLowerCase().trim().equals("bye");

            // Update taskCount value from class-level member in Task
            taskCount = Task.getNumberOfTasks();

            // Prints the list of tasks stored if "list" is called
            if (command.toLowerCase().trim().equals("list")) {
                printList(listOfTasks);
            } else if (command.toLowerCase().contains("done")) {
                // Update done status for indicated task
                doneTask(command, listOfTasks);
            } else if (!saidBye) {
                // Store the command into the array as a task if it's none of the above
                try {
                    addTask(command, listOfTasks, taskCount);
                } catch (InvalidCommandException e) {
                    botSpeak("☹ Sorry but I don't understand that at all. Try again?");
                } catch (StringIndexOutOfBoundsException e) {
                    botSpeak("Date not found for deadline/event! Please add it using /by or /at");
                }
            }
        } while (!saidBye);
    }

    /**
     * Identifies the type of task given by user and add into the list
     *
     * @param command     user input at terminal
     * @param listOfTasks Array containing tasks inserted by user
     * @param taskCount   Store the amount of tasks inserted
     */
    private static void addTask(String command, Task[] listOfTasks, int taskCount) throws InvalidCommandException {
        String task;
        String date = " ";
        boolean isDateIncluded = false;

        // Identifies if the task is
        TaskType taskType = getTaskType(command);

        // Find the part of string where user types the date
        int dateStringIndex = command.indexOf("/");


        // Date is required when its a deadline/event
        boolean isDateRequired = (taskType == TaskType.DEADLINE) || (taskType == TaskType.EVENT);

        // Date is included if "/" is found in the user's command
        if (isDateRequired && (dateStringIndex > 0)) {
            // Extract the date of the deadline/event
            date = command.substring(dateStringIndex + "/by".length()).trim();
            isDateIncluded = true;
        }

        // Creates new object based on the type of the task
        switch (taskType) {
        case TODO:
            // Extract the string after "todo"
            task = command.trim().substring("todo".length()).trim();
            try {
                listOfTasks[taskCount] = new ToDo(task);
            } catch (InvalidCommandException e) {
                botSpeak("☹ OH NO! The description of todo cannot be empty!");
            }
            break;
        case DEADLINE:
            try {
                // Extract the string between "deadline" and "/"
                task = command.trim().substring("deadline".length(), dateStringIndex).trim();
                listOfTasks[taskCount] = new Deadline(task, date);
            } catch (InvalidCommandException e) {
                botSpeak("☹ OH NO! The description of deadline cannot be empty!");
            } catch (InvalidDateException e) {
                botSpeak("No date is found for this deadline! Try adding a date after /by or /at");
            }
            break;
        case EVENT:
            try {
                task = command.trim().substring("event".length(), dateStringIndex).trim();
                listOfTasks[taskCount] = new Event(task, date);
            } catch (InvalidCommandException e){
                botSpeak("☹ OH NO! The description of event cannot be empty!");
            } catch (InvalidDateException e) {
                botSpeak("No date is found for this deadline! Try adding a date after /at or /at");
            }
            break;
        default:
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

        // Check what is the type of the task given
        if (commandModified.startsWith("todo")) {
            taskType = TaskType.TODO;
        } else if (commandModified.startsWith("deadline")) {
            taskType = TaskType.DEADLINE;
        } else if (commandModified.startsWith("event")) {
            taskType = TaskType.EVENT;
        } else {
            // taskType is NORMAL when user did not input specific type at the start
            taskType = TaskType.NORMAL;
        }
        return taskType;
    }

    /**
     * Prints out the list of tasks stored
     *
     * @param listOfTasks Array containing tasks inserted by user
     */
    public static void printList(Task[] listOfTasks) {
        int taskCount = Task.getNumberOfTasks();

        // Notify the user if no tasks has been added yet
        if (taskCount == 0) {
            botSpeak("No tasks has been added yet. Try adding something!");
        } else {
            // Prints out the list of commands with respective index number
            printDivider();
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + "." + listOfTasks[i]);
            }
            printDivider();
        }
    }

    /**
     * Allows user to mark tasks as done
     *
     * @param command     The command input by user
     * @param listOfTasks Array containing tasks inserted by user
     */
    public static void doneTask(String command, Task[] listOfTasks) {
        int taskCount = Task.getNumberOfTasks();

        if (isDoneValid(command)) {
            // Extract the index number of the task to be marked as done
            int taskIndex = Integer.parseInt(command.substring(command.toLowerCase().indexOf("done") + 4).trim()) - 1;

            // Make task as done if the task index inputted is at least 0 and less than the number of tasks inserted
            if ((taskIndex >= 0) && (taskIndex < taskCount)) {
                markAsDone(listOfTasks, taskIndex);
            } else {
                botSpeak("Task not found. Make sure you input the correct task index number!");
            }
        } else {
            botSpeak("No index number inserted. Please try again!");
        }
    }

    /**
     * Mark the task in the list as done
     *
     * @param listOfTasks Array containing tasks inserted by user
     * @param taskIndex   Index of the task indicated
     */
    private static void markAsDone(Task[] listOfTasks, int taskIndex) {
        // Inform the user if the task input has already been done
        if (listOfTasks[taskIndex].isDone) {
            botSpeak("This task has already been done! Good luck completing others!!!");
        } else {
            // Mark the task as done
            listOfTasks[taskIndex].isDone = true;
            botSpeak("Good job! I have marked this task as done:\n"
                    + listOfTasks[taskIndex]);
        }
    }

    /**
     * Checks if the "done" command input by user is correct
     * It is correct if it does not have blank space and non-digits after "done" input
     *
     * @param sentence String of command inserted by user
     * @return logic true if the "done" command is valid
     */
    public static boolean isDoneValid(String sentence) {
        // Extract the string after "done" and convert it to array of characters
        String stringAfterDone = sentence.substring(sentence.toLowerCase().indexOf("done") + 4).trim();
        char[] charAfterDone = stringAfterDone.toCharArray();

        // Return false if the substring after "done" only contains empty space
        if (stringAfterDone.isEmpty()) {
            return false;
        }
        // Return false if the substring after "done" are not digits
        for (char character : charAfterDone) {
            if (!Character.isDigit(character)) {
                return false;
            }
        }
        return true;
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
     * Prints the line divider
     */
    public static void printDivider() {
        System.out.println("*******************************************************************************");
    }

    /**
     * Allows user to input command
     */
    public static String inputCommand() {
        String command;
        Scanner in = new Scanner(System.in);

        command = in.nextLine();

        return command;
    }
}