import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Helps process and identify commands input from the user
 */
public class Parser {

    /**
     * Identify the type of command inserted by the user
     *
     * @param command Raw command input from the user
     * @return command types of the input
     */
    public static CommandType getCommandType(String command) {
        String commandModified = command.trim().toLowerCase();

        if (commandModified.equals("bye")) {
            return CommandType.BYE;
        } else if (commandModified.equals("list")) {
            return CommandType.LIST;
        } else if (commandModified.contains("done")) {
            return CommandType.DONE;
        } else if (commandModified.startsWith("delete")) {
            return CommandType.DELETE;
        } else {
            return CommandType.TASK;
        }
    }

    /**
     * Process the line from the data file to identify the
     * type of task in that line
     *
     * @param line data from the file
     * @return task type for that line (event, deadline, todo)
     */
    public static TaskType getTaskTypeFromFile(String line) {
        // Checks the type of the task given
        if (line.startsWith(Symbols.TODO_INDICATOR)) {
            return TaskType.TODO;
        } else if (line.startsWith(Symbols.DEADLINE_INDICATOR)) {
            return TaskType.DEADLINE;
        } else if (line.startsWith(Symbols.EVENT_INDICATOR)) {
            return TaskType.EVENT;
        } else {
            //taskType = taskType is NONE when user did not input specific type at the start
            return TaskType.NONE;
        }
    }
    /**
     * Identifies and return the type of task the user has inserted
     *
     * @param command Raw command input from the user
     * @return the type of the task (event, deadline, todo)
     */
    public static TaskType getTaskType(String command) {
        String commandModified = command.trim().toLowerCase();

        // Checks the type of the task given
        if (commandModified.startsWith("todo")) {
            return TaskType.TODO;
        } else if (commandModified.startsWith("deadline")) {
            return TaskType.DEADLINE;
        } else if (commandModified.startsWith("event")) {
            return TaskType.EVENT;
        } else {
            // taskType is NONE when user did not input specific type at the start
            return TaskType.NONE;
        }
    }

    /**
     * Checks if the "done" or "delete" command input by user is correct
     * It is correct if it does not have blank space and non-digits after "done" or "delete" input
     *
     * @param sentence String of command inserted by user
     * @return logic true if the command is valid
     */
    public static boolean isIndexValid(String sentence, int taskIndexPosition) {
        String stringAfterCommand;

        // Extract the string and convert it to array of characters
        stringAfterCommand = sentence.substring(taskIndexPosition).trim();

        char[] charAfterCommand = stringAfterCommand.toCharArray();

        // Return false if the substring after "done" only contains empty space
        if (stringAfterCommand.isEmpty()) {
            return false;
        }
        // Return false if the substring after "done" are not digits
        for (char character : charAfterCommand) {
            if (!Character.isDigit(character)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the date starts with the 3-letter month abbreviation
     *
     * @param date String containing the date specified
     * @return true if it starts with month and false otherwise
     */
    public static boolean startsWithMonth(String date) {
        String[] monthList = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
                "Aug", "Sep", "Oct", "Nov", "Dec"};

        for (String month : monthList) {
            if (date.startsWith(month)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Parses the date from string into a LocalDate object if the format is correct
     *
     * @param date String containing the date specified
     * @return LocalDate object containing date information
     */
    public static LocalDate parseDate(String date) throws DateTimeParseException {
        if (Parser.startsWithMonth(date)) {
            // Occurs when parsing date in data file
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            return LocalDate.parse(date);
        }
    }

    /**
     * Parse the date into LocalDate if it is the correct format and return
     * the date in correct format to be printed
     *
     * @param date String containing the date specified
     * @param localDate LocalDate object passed in
     * @return date in the correct format
     */
    public static String formatDate(String date, LocalDate localDate) {
        try {
            // Parse date into LocalDate based on different formats
            localDate = parseDate(date);
            // To be printed in "MMM d yyyy" format later
            return localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch(DateTimeParseException e) {
            // Returns the raw date input if non-parsable format detected
            return date;
        }
    }
}
