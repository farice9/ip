import java.util.ArrayList;

/**
 * Contains all the methods that can be called to modify the list of tasks
 */
public class TaskList {
    /**
     * Process user's command to mark task as done
     *
     * @param command  Raw command input from the user
     * @param listOfTasks ArrayList containing list of tasks
     */
    public static void doneTask(String command, ArrayList<Task> listOfTasks) {
        int taskCount = Task.getNumberOfTasks();
        int taskIndexPosition = command.toLowerCase().indexOf("done") + "done".length();

        if (Parser.isIndexValid(command, taskIndexPosition)) {
            // Extract the index number of the task to be marked as done
            int taskIndex = Integer.parseInt(command.substring(taskIndexPosition).trim()) - 1;

            // Mark task as done if the task index inputted is at least 0 and less than the number of tasks inserted
            boolean isTaskIndexWithinRange = ((taskIndex >= 0) && (taskIndex < taskCount));

            if (isTaskIndexWithinRange) {
                markAsDone(listOfTasks, taskIndex);
            } else {
                Ui.printInvalidDoneIndexError();
            }
        } else {
            Ui.printIndexInputNotDetectedError();
        }
    }

    /**
     * Marks the indicated task in the list as done
     *
     * @param listOfTasks ArrayList containing list of tasks
     * @param taskIndex   Index of the task indicated
     */
    public static void markAsDone(ArrayList<Task> listOfTasks, int taskIndex) {
        // Inform the user if the task input has already been done
        if (listOfTasks.get(taskIndex).isDone) {
            Ui.printTaskAlreadyDoneMessage();
        } else {
            // Mark the task as done
            listOfTasks.get(taskIndex).doneTask();
            Ui.printMarkAsDoneMessage(listOfTasks, taskIndex);
        }
    }

    /**
     * Deletes the indicated task in the list
     *
     * @param listOfTasks ArrayList containing the list of task
     * @param command Raw command input from the user
     */
    public static void deleteTask(ArrayList<Task> listOfTasks, String command) {
        int taskCount = Task.getNumberOfTasks();
        int taskIndexPosition = command.toLowerCase().indexOf("delete") + "delete".length();

        // Check if there's an integer input after "delete"
        if (Parser.isIndexValid(command, taskIndexPosition)) {
            // Obtain the index of the task to be deleted
            int taskIndex = Integer.parseInt(command.substring(taskIndexPosition).trim()) - 1;

            // Perform the delete operation is the index is within range
            boolean isTaskIndexWithinRange = ((taskIndex >= 0) && (taskIndex < taskCount));

            if (isTaskIndexWithinRange) {
                Task taskToBeRemoved = listOfTasks.get(taskIndex);

                listOfTasks.remove(taskIndex);
                Task.reduceNumberOfTasks();

                Ui.printDeleteResult(taskToBeRemoved);
            } else {
                Ui.printDeleteTaskNotFoundError();
            }
        } else {
            Ui.printIndexInputNotDetectedError();
        }
    }

    /**
     * Identifies the type of task given by user and add into the list
     *
     * @param command Raw command input from the user
     * @param listOfTasks ArrayList containing list of tasks
     * @throws InvalidCommandException exception due to commands without specifying the type
     */
    public static void addTask(String command, ArrayList<Task> listOfTasks) throws InvalidCommandException {
        String task;

        // Identifies the task type
        TaskType taskType = Parser.getTaskType(command);

        int taskCount = Task.getNumberOfTasks();

        // Creates new object based on the type of the task
        switch (taskType) {
        case TODO:
            addTodoTask(command, listOfTasks, taskCount);
            break;
        case DEADLINE:
            addDeadlineTask(command, listOfTasks, taskCount);
            break;
        case EVENT:
            addEventTask(command, listOfTasks, taskCount);
            break;
        default:
            // Exception due to non-specific task type
            throw new InvalidCommandException();
        }
    }

    /**
     * Adds an event type task to the list of tasks
     *
     * @param command Raw command input from the user
     * @param listOfTasks ArrayList containing list of tasks
     * @param taskCount Number of tasks in the list
     */
    public static void addEventTask(String command, ArrayList<Task> listOfTasks, int taskCount) {
        try {
            listOfTasks.add(new Event(command));
            listOfTasks.get(taskCount).printAddResult();
        } catch (InvalidCommandException e) {
            Ui.printEmptyEventDescriptionError();
        } catch (InvalidDateException e) {
            Ui.printEmptyEventDateError();
        }
    }

    /**
     * Adds a deadline type task to the list of tasks
     *
     * @param command Raw command input from the user
     * @param listOfTasks ArrayList containing list of tasks
     * @param taskCount Number of tasks in the list
     */
    public static void addDeadlineTask(String command, ArrayList<Task> listOfTasks, int taskCount) {
        try {
            listOfTasks.add(new Deadline(command));
            listOfTasks.get(taskCount).printAddResult();
        } catch (InvalidCommandException e) {
            Ui.printEmptyDeadlineDescriptionError();
        } catch (InvalidDateException e) {
            Ui.printEmptyDeadlineDateError();
        }
    }

    /**
     * Adds a todo type task to the list of tasks
     * @param command Raw command input from the user
     * @param listOfTasks ArrayList containing list of tasks
     * @param taskCount Number of tasks in the list
     */
    public static void addTodoTask(String command, ArrayList<Task> listOfTasks, int taskCount) {
        String task;
        // Extract the string after "todo"
        task = command.trim().substring("todo".length()).trim();
        try {
            listOfTasks.add(new ToDo(task));
            listOfTasks.get(taskCount).printAddResult();
        } catch (InvalidCommandException e) {
            Ui.printEmptyTodoError();
        }
    }
}
