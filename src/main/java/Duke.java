import java.util.Scanner;

public class Duke {
    // Main function
    public static void main(String[] args) {
        greet();
        echo();
    }

    // Basic greeting - Duke greets user
    public static void greet(){
        botSpeak("Hey mate! Nice to meet you. I'm Duke\nHow can I help you?");
    }

    // Basic exit - bot says goodbye
    public static void exit(){
        botSpeak("Sad to see you leave. Goodbye! Hope to see you again!");
    }

    // Reflect the processing of the commands given by the user
    public static void echo(){
        String command;
        boolean saidGoodbye;

        // Stores the commands given
        String[] listOfTasks = new String[100];
        int taskCount = 0;

        // Repeatedly receive user command until "bye" is given
        do {
            // Collect user's command
            command = giveCommand();

            // Checks if the command is bye
            saidGoodbye = command.toLowerCase().equals("bye");

            // Prints the list of tasks stored if "list" is called
            if (command.toLowerCase().equals("list")){
                // Notify the user if nothing has been added yet
                if (taskCount == 0) {
                    botSpeak("Nothing has been added yet. Try adding something!");
                }
                else {
                    printDivider();
                    for (int i=0 ; i < taskCount ; i++){
                        System.out.println((i+1) + ". " + listOfTasks[i]);
                    }
                    printDivider();
                }
            }
            else {
                listOfTasks[taskCount++] = command;
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
        System.out.println("*************************************************************");
    }

    // Allows user to give command
    public static String giveCommand(){
        String command;
        Scanner in = new Scanner(System.in);

        command = in.nextLine();

        return command;
    }
}