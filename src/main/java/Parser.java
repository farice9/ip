public class Parser {

    /**
     * Identify the type of command inserted by the user
     *
     * @param command raw input from the user
     * @return command types of the input
     */
    public static CommandType getCommandType(String command) {
        String commandModified = command.trim().toLowerCase();

        if (command.toLowerCase().trim().equals("bye")) {
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
     * Identifies and return the type of task the user has inserted
     *
     * @param command Command that the user input
     * @return the type of the task (event, deadline, todo)
     */
    public static TaskType getTaskType(String command) {
        TaskType taskType;

        String commandModified = command.trim().toLowerCase();

        // Checks the type of the task given
        if (commandModified.startsWith("todo")) {
            taskType = TaskType.TODO;
        } else if (commandModified.startsWith("deadline")) {
            taskType = TaskType.DEADLINE;
        } else if (commandModified.startsWith("event")) {
            taskType = TaskType.EVENT;
        } else {
            // taskType is NONE when user did not input specific type at the start
            taskType = TaskType.NONE;
        }
        return taskType;
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
}
