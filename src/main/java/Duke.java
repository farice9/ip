import java.util.Scanner;
import java.util.Arrays;

/**
 * An interactive bot that performs various tasks based on user command
 *
 * Last updated : 2 September 2020
 *
 * Functions implemented:
 * 1) Adding tasks to a list
 * 2) Printing the list of tasks stored
 * 3) Indicating which task is done
 * 4) Different type of tasks (event, deadline, todo)
 *
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
    public static void greet(){
        botSpeak("Hey mate! Nice to meet you, I'm Duke!\nHow can I help you?");
    }

    /**
     * Prints goodbye message
     */
    public static void exit(){
        botSpeak("Goodbye & have a nice day! Hope to see you again!");
    }

    /**
     * Process the commands given by the user
     * Possible commands : list, bye, done (_digit_), (any string)
     */
    public static void processCommand(){
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
            if (command.toLowerCase().trim().equals("list")){
                printList(listOfTasks);
            }
            // Update done status for indicated task
            else if (command.toLowerCase().contains("done")){
                doneTask(command , listOfTasks);
            }
            // Store the command into the array as a task if it's none of the above
            else if (!saidBye){
                addTask(command, listOfTasks, taskCount);
            }
        } while(!saidBye);


    }

    /**
     * Identifies the type of task given by user and add into the list
     *
     * @param command user input at terminal
     * @param listOfTasks Array containing tasks inserted by user
     * @param taskCount Store the amount of tasks inserted
     */
    private static void addTask(String command, Task[] listOfTasks, int taskCount) {
        //listOfTasks[taskCount] = new Task(command);
        TaskType taskType;
        String initialCommand = command.trim().toLowerCase();
        String task;
        int dateStringIndex; // Stores the index of the "/"
        String date;
        boolean isDateRequired;
        boolean isDateIncluded = false;

        // Check what is the type of the task given
        if (initialCommand.startsWith("todo")){
            taskType = TaskType.TODO;
        }
        else if (initialCommand.startsWith("deadline")){
            taskType = TaskType.DEADLINE;
        }
        else if (initialCommand.startsWith("event")){
            taskType = TaskType.EVENT;
        }
        else {
            taskType = TaskType.NORMAL;
        }

        // Find the part of string where user types the date
        dateStringIndex = command.indexOf("/");

        // Date is required when its a deadline/event
        isDateRequired = (taskType == TaskType.DEADLINE) || (taskType == TaskType.EVENT);

        if (isDateRequired && (dateStringIndex > 0)) {
            isDateIncluded = true;
        }

        // Extract the date of the deadline/event
        date = command.substring(dateStringIndex + "/by".length()).trim();

        // Creates new object based on the type of the task
        switch (taskType){
        case TODO:
            // Extract the string after "todo"
            task = command.trim().substring("todo".length()).trim();
            listOfTasks[taskCount] = new ToDo(task);
            break;
        case DEADLINE:
            if (isDateIncluded) {
                // Extract the string between "deadline" and "/"
                task = command.trim().substring("deadline".length(),dateStringIndex).trim();
                listOfTasks[taskCount] = new Deadline(task, date);
            }
            break;
        case EVENT:
            if (isDateIncluded) {
                task = command.trim().substring("event".length(),dateStringIndex).trim();
                listOfTasks[taskCount] = new Event(task, date);
            }
            break;
        default:
            listOfTasks[taskCount] = new Task(command);
        }

        // Task is added successfully when it does not require a date or if the date is included
        if (!isDateRequired || isDateIncluded) {
            printDivider();
            System.out.println("Alrighty! I've added the following task:");
            System.out.println(listOfTasks[taskCount]);
            Task.printNumberOfTasks(); // Inform user how many tasks they have
            printDivider();
        }
        else {
            botSpeak("Task not added. Please indicate a date using /by or /at followed by the date!");
        }
    }

    /**
     * Prints out the list of tasks stored
     *
     * @param listOfTasks Array containing tasks inserted by user
     */
    public static void printList(Task[] listOfTasks){
        int taskCount = Task.getNumberOfTasks();

        // Notify the user if no tasks has been added yet
        if (taskCount== 0) {
            botSpeak("No tasks has been added yet. Try adding something!");
        }
        // Prints out the list of commands with respective index number
        else {
            printDivider();
            System.out.println("Here are the tasks in your list:");
            for (int i=0 ; i < taskCount; i++){
                System.out.println((i+1) + "." + listOfTasks[i]);
            }
            printDivider();
        }
    }

    /**
     * Allows user to mark tasks as done
     *
     * @param command The command input by user
     * @param listOfTasks Array containing tasks inserted by user
     */
    public static void doneTask(String command,Task[] listOfTasks){
        int taskCount = Task.getNumberOfTasks();

        if (isDoneValid(command)){
            // Extract the index number of the task to be marked as done
            int taskIndex = Integer.parseInt(command.substring(command.toLowerCase().indexOf("done") + 4).trim()) - 1;

            // Make sure task index input is minimally 0 and less than the number of tasks inserted
            if ((taskIndex >= 0) && (taskIndex < taskCount)){
                // Inform the user if the task input has already been done
                if (listOfTasks[taskIndex].isDone){
                    botSpeak("This task has already been done! Good luck completing others!!!");
                }
                else {
                    // Mark the task as done
                    listOfTasks[taskIndex].isDone = true;
                    botSpeak("Good job! I have marked this task as done:\n" +
                            listOfTasks[taskIndex]);
                }
            }
            else {
                botSpeak("Task not found. Make sure you input the correct task index number!");
            }
        }
        else {
            botSpeak("No index number inserted. Please try again!");
        }
    }

    /**
     * Checks if the "done" command input by user is correct
     * It is correct if it does not have blank space and non-digits
     *
     * @param sentence String of command inserted by user
     * @return logic true if the "done" command is valid
     */
    public static boolean isDoneValid(String sentence){
        // Extract the string after "done" and convert it to array of characters
        String stringAfterDone = sentence.substring(sentence.toLowerCase().indexOf("done") + 4).trim();
        char[] charAfterDone = stringAfterDone.toCharArray();

        // Return false if the substring after "done" only contains empty space
        if (stringAfterDone.isEmpty()){
            return false;
        }
        // Return false if the substring after "done" are not digits
        for (char character: charAfterDone){
            if (!Character.isDigit(character)){
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
    public static void botSpeak(String sentence){
        printDivider();
        System.out.println(sentence);
        printDivider();
    }

    /**
     * Prints the line divider
     */
    public static void printDivider(){
        System.out.println("**************************************************************************");
    }

    /**
     * Allows user to input command
     */
    public static String inputCommand(){
        String command;
        Scanner in = new Scanner(System.in);

        command = in.nextLine();

        return command;
    }
}