import java.util.ArrayList;

public class TaskList {


    /**
     * Allows user to mark tasks as done
     *
     * @param command     The command input by user
     * @param listOfTasks Array containing tasks inserted by user
     */
    public static void doneTask(String command, ArrayList<Task> listOfTasks) {
        int taskCount = Task.getNumberOfTasks();
        int taskIndexPosition = command.toLowerCase().indexOf("done") + "done".length();

        if (Parser.isIndexValid(command, taskIndexPosition)) {
            // Extract the index number of the task to be marked as done
            int taskIndex = Integer.parseInt(command.substring(taskIndexPosition).trim()) - 1;

            // Make task as done if the task index inputted is at least 0 and less than the number of tasks inserted
            if ((taskIndex >= 0) && (taskIndex < taskCount)) {
                markAsDone(listOfTasks, taskIndex);
            } else {
                Ui.printInvalidDoneIndexError();
            }
        } else {
            Ui.printIndexInputNotDetectedError();
        }
    }

    /**
     * Mark the task in the list as done
     *
     * @param listOfTasks Array containing tasks inserted by user
     * @param taskIndex   Index of the task indicated
     */
    public static void markAsDone(ArrayList<Task> listOfTasks, int taskIndex) {
        // Inform the user if the task input has already been done
        if (listOfTasks.get(taskIndex).isDone) {
            Ui.printTaskAlreadyDoneMessage();
        } else {
            // Mark the task as done
            listOfTasks.get(taskIndex).isDone = true;
            Ui.printMarkAsDoneMessage(listOfTasks, taskIndex);
        }
    }

    /**
     * Deletes indicated task in the list
     *
     * @param listOfTasks ArrayList containing the list of task
     * @param command Command input by the user
     */
    public static void deleteTask(ArrayList<Task> listOfTasks, String command) {
        int taskCount = Task.getNumberOfTasks();
        int taskIndexPosition = command.toLowerCase().indexOf("delete") + "delete".length();

        // Check if there's an integer input after "delete"
        if (Parser.isIndexValid(command, taskIndexPosition)) {
            // Obtain the index of the task to be deleted
            int taskIndex = Integer.parseInt(command.substring(taskIndexPosition).trim()) - 1;

            // Perform the delete operation is the index is within range
            if ((taskIndex >= 0) && (taskIndex < taskCount)) {
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
     * @param command     user input at terminal
     * @param listOfTasks ArrayList containing tasks inserted by user
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
            // Extract the string after "todo"
            task = command.trim().substring("todo".length()).trim();
            try {
                listOfTasks.add(new ToDo(task));
                listOfTasks.get(taskCount).printAddResult();
            } catch (InvalidCommandException e) {
                Ui.printEmptyTodoError();
            }
            break;
        case DEADLINE:
            // Command inserted by user will be processed and added into the list of tasks
            try {
                listOfTasks.add(new Deadline(command));
                listOfTasks.get(taskCount).printAddResult();
            } catch (InvalidCommandException e) {
                Ui.printEmptyDeadlineDescriptionError();
            } catch (InvalidDateException e) {
                Ui.printEmptyDeadlineDateError();
            }
            break;
        case EVENT:
            try {
                listOfTasks.add(new Event(command));
                listOfTasks.get(taskCount).printAddResult();
            } catch (InvalidCommandException e) {
                Ui.printEmptyEventDescriptionError();
            } catch (InvalidDateException e) {
                Ui.printEmptyEventDateError();
            }
            break;
        default:
            // Exception due to non-specific task type
            throw new InvalidCommandException();
        }
    }
}
