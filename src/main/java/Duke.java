import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    // Main function
    public static void main(String[] args) {
        greet();
        processCommand();
    }

    // Basic greeting - bot greets user
    public static void greet(){
        botSpeak("Hey mate! Nice to meet you. I'm Duke\nHow can I help you?");
    }

    // Basic exit - bot says goodbye
    public static void exit(){
        botSpeak("Goodbye & have a nice day! Hope to see you again!");
    }

    // Process the commands given by the user
    public static void processCommand(){
        String command;
        boolean saidGoodbye;

        // Stores the commands given
        Task[] listOfTasks = new Task[100]; // Can store 100 strings
        int taskCount; // Keep track the amount of tasks inputted

        // Repeatedly receive user command until "bye" is given
        do {
            // Collect user's command
            command = inputCommand();

            // Checks if the command is bye
            saidGoodbye = command.toLowerCase().trim().equals("bye");

            // Update taskCount value from class-level member in Task
            taskCount = Task.getNumberOfTasks();

            // Prints the list of tasks stored if "list" is called
            if (command.toLowerCase().trim().equals("list")){
                // Notify the user if nothing has been added yet
                if (taskCount== 0) {
                    botSpeak("Nothing has been added yet. Try adding something!");
                }
                // Prints out the list of commands with respective index number
                else {
                    printDivider();
                    System.out.println("Here are the tasks in your list:");
                    for (int i=0 ; i < taskCount; i++){
                        System.out.println((i+1) + "." + "[" + listOfTasks[i].getStatusIcon() + "] " + listOfTasks[i].description);
                    }
                    printDivider();
                }
            }
            // Update done status for some tasks
            else if (command.toLowerCase().contains("done")){

                if (isDoneValid(command)){
                    // Extract the index number of the task to be marked as done
                    int taskIndex = Integer.parseInt(command.substring(command.toLowerCase().indexOf("done") + 4).trim()) - 1;

                    // Make sure task index input is minimally 0 and less than the number of tasks inserted
                    if (taskIndex >= 0 && taskIndex < taskCount){
                        // Inform the user if the task input has already been done
                        if (listOfTasks[taskIndex].isDone){
                            botSpeak("This task has already been done! Good luck completing others!!!");
                        }
                        else {
                            // Mark the task as done
                            listOfTasks[taskIndex].isDone = true;
                            botSpeak("Good job! I have marked this task as done:\n" +
                                    "[" + listOfTasks[taskIndex].getStatusIcon() + "] " +listOfTasks[taskIndex].description);
                        }
                    }
                    else {
                        botSpeak("Invalid index number. Make sure you input the correct task index!");
                    }
                }
                else {
                    botSpeak("No number inserted. Please try again!");
                }
            }
            // Store the command into the array
            else if (!saidGoodbye){
                listOfTasks[taskCount] = new Task(command);
                botSpeak("added: " + command);
            }
        } while(!saidGoodbye);

        exit();
    }

    public static boolean isDoneValid(String sentence){
        String stringAfterDone = sentence.substring(sentence.toLowerCase().indexOf("done") + 4).trim();
        char[] charAfterDone = stringAfterDone.toCharArray();

        if (stringAfterDone.isEmpty()){
            return false;
        }
        for (char character: charAfterDone){
            if (!Character.isDigit(character)){
                return false;
            }
        }

        return true;
    }

    // Prints out what the bot says with divider on top and bottom
    public static void botSpeak(String sentence){
        printDivider();
        System.out.println(sentence);
        printDivider();
    }

    // Prints the line divider
    public static void printDivider(){
        System.out.println("*********************************************************************");
    }

    // Allows user to input command
    public static String inputCommand(){
        String command;
        Scanner in = new Scanner(System.in);

        command = in.nextLine();

        return command;
    }
}