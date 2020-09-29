/**
 * Contains messages to be printed in the user interface
 */
public class Message {
    // Messages involved when loading data file
    public static final String LOADING_ERROR = "Oh no! There's a problem loading file content";
    public static final String LOADING_FILE = "Loading your previous tasks, hold on! ...";
    public static final String DONE_LOADING = "... Storage file content loaded!";
    public static final String READ_FILE_IDENTIFICATION_ERROR = "There's a problem identifying this task while reading file";
    public static final String READ_FILE_ADD_TASK_ERROR = "Problem adding this task from storage file";
    public static final String READ_FILE_DATE_ERROR = "Problem adding task due to incorrect date input";
    public static final String WRITE_FILE_ERROR = "There's a problem with writing the file";
    public static final String CREATE_NEW_FILE =  "File not found, creating one now!";

    // Simple interaction messages
    public static final String GREET = "Hey mate! Nice to meet you, I'm Duke!\nHow can I help you?\n";
    public static final String GOODBYE = "Goodbye & have a nice day! Hope to see you again!";

    // Error messages when detecting task input
    public static final String INVALID_TASK_TYPE_ERROR = "☹ Sorry but I don't understand that task at all. Try again?";
    public static final String EMPTY_TODO_ERROR = "☹ OH NO! The description of todo cannot be empty!";
    public static final String EMPTY_DEADLINE_DESCRIPTION_ERROR = "☹ OH NO! The description of deadline cannot be empty!";
    public static final String EMPTY_DEADLINE_DATE_ERROR = "No date is found for this deadline! Try adding a date after /by";
    public static final String EMPTY_EVENT_DESCRIPTION_ERROR = "☹ OH NO! The description of event cannot be empty!";
    public static final String EMPTY_EVENT_DATE_ERROR = "No date is found for this event! Try adding a date after /at";

    // Error messages when using "delete" function
    public static final String DELETE_TASK_NOT_FOUND_ERROR = "Task not found! Nothing is there to be deleted";
    public static final String INDEX_INPUT_NOT_DETECTED_ERROR = "No index number input detected. Please try again!";

    // Messages when using "list" function
    public static final String EMPTY_TASK_LIST_MESSAGE = "No tasks has been added yet. Try adding something!";

    // Messages when using "done" function
    public static final String INVALID_DONE_INDEX_ERROR = "Task not found. Nothing is marked as done. Check your task index number input!";
    public static final String TASK_ALREADY_DONE_MESSAGE = "This task has already been done! Good luck completing others!!!";
    public static final String MARK_AS_DONE_MESSAGE = "Good job! I have marked this task as done:";

    // Graphics involved in the UI
    public static final String LINE_DIVIDER = "*******************************************************************************";

    public static final String USER_COMMAND_GUIDE =
            "Here are the commands you can use to interact with me:\n" +
            "help                       : Prints out a simple user command guide!\n" +
            "todo <task>                : To store what needs to be done!\n" +
            "deadline <task> /by <date> : To keep track of your deadlines!\n" +
            "event <task> /at <date>    : To keep in mind upcoming important events!\n" +
            "list                       : Lists out all the tasks you have so far\n" +
            "done <index number>        : Marks task as done\n" +
            "delete <index number>      : Deletes the indicated task from the list\n" +
            "find <keyword>             : Find tasks with the keyword\n" +
            "bye                        : Exits this application\n" +
            "\nNote: Insert <date> in YYYY-MM-DD format for me to understand it!\n";
}
