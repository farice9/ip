import java.util.Scanner;

public class Duke {
    // Main function
    public static void main(String[] args) {
        greet();
        echo();
        exit();
    }

    // Basic greeting - Duke greets user
    public static void greet(){
        botSpeak("Hey mate! Nice to meet you. I'm Duke\nHow can I help you?");
    }

    // Basic exit - bot says goodbye
    public static void exit(){
        botSpeak("Sad to see you leave. Goodbye! Hope to see you again!");
    }

    // Echoes back user's commands
    public static void echo(){
        String command;
        boolean saidGoodbye;

        // Echoes back user's command until user says "bye"
        do {
            // Collect user's command
            command = giveCommand();

            // Checks if the command is bye
            saidGoodbye = command.toLowerCase().equals("bye");

            // Echoes back command (given that it is not "bye")
            if (!saidGoodbye) {
                botSpeak(command);
            }
        } while(!saidGoodbye);
    }

    // Prints out what the bot says
    public static void botSpeak(String sentence){
        System.out.println("*************************************************************");
        System.out.println(sentence);
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
