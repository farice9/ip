public class Ui {

    public static void printLoadingError() {
        System.out.println(Message.LOADING_ERROR);
    }

    public static void greet() {
        printDivider();
        System.out.println(Message.GREET);
        printDivider();
    }

    public static void printUserGuide() {
        System.out.println("Here are some commands you can use to interact with me:\n" +
                "todo <task>                : Store what needs to be done \n" +
                "deadline <task> /by <date> : Keep track of your deadlines!\n" +
                "event <task> /at <date>    : To keep in mind upcoming important events!\n" +
                "list                       : To list out all the tasks you have so far\n" +
                "done <integer number>      : To mark a task as done\n" +
                "delete <integer number>    : To delete a task from the list\n");
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
        botSpeak(Message.INVALID_TASK_TYPE);
    }

    public static void printWriteFileError() {
        System.out.println(Message.WRITE_FILE_ERROR);
    }



}
