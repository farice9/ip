public class Duke {
    public static void main(String[] args) {
        greet();
    }

    // Level 0 : Basic greeting - Duke greets user and exit
    public static void greet(){
        System.out.println("--------------------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
    }

    // Default : Print Duke logo + greet
    public static void logo(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}
