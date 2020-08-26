import java.util.Scanner;

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
        botSpeak("Sad to see you leave. Goodbye! Hope to see you again!");
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
            saidGoodbye = command.toLowerCase().equals("bye");

            // Update taskCount value from class-level member in Task
            taskCount = Task.getNumberOfTasks();

            // Prints the list of tasks stored if "list" is called
            if (command.toLowerCase().equals("list")){
                // Notify the user if nothing has been added yet
                if (taskCount== 0) {
                    botSpeak("Nothing has been added yet. Try adding something!");
                }
                // Prints out the list of commands with respective index number
                else {
                    printDivider();
                    for (int i=0 ; i < taskCount; i++){
                        System.out.println((i+1) + ". " + listOfTasks[i].description);
                    }
                    printDivider();
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