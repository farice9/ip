import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Contains all the methods required for file processing
 */
public class DukeFiles {
    private static final String dukeFilepath = "tasks.txt";

    /**
     * Update the txt file with the list of tasks stored
     *
     * @param listOfTasks ArrayList containing list of tasks
     * @throws IOException Error caused when writing file
     */
    public static void writeToFile(ArrayList<Task> listOfTasks) throws IOException {
        FileWriter fw = new FileWriter(dukeFilepath);

        for (Task task : listOfTasks) {
            fw.write(task.toString() + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * To be run at the start of program to initialize the ArrayList needed for operation
     * by loading the data from saved file
     *
     * @param listOfTasks ArrayList containing list of tasks
     * @throws IOException Error caused when writing file
     */
    public static void initializeFile(ArrayList<Task> listOfTasks) throws IOException {
        try {
            loadFileContent(listOfTasks);
        } catch (FileNotFoundException e) {
            File f = new File(dukeFilepath);
            f.createNewFile();
            Ui.printCreateNewFile();
        }
    }

    /**
     * To load the content of the file onto the ArrayList of the program
     *
     * @param listOfTasks ArrayList containing list of tasks
     * @throws FileNotFoundException Error caused by file not found at the specified filepath
     */
    public static void loadFileContent(ArrayList<Task> listOfTasks) throws FileNotFoundException {
        File f = new File(dukeFilepath);
        Scanner s = new Scanner(f);

        Ui.printDivider();
        Ui.printLoadingFile();
        while (s.hasNext()) {
            String line = s.nextLine();

            int taskCount = Task.getNumberOfTasks();
            int initialLength = "[ ][ ]".length();
            String description;
            String date;

            TaskType taskType = Parser.getTaskTypeFromFile(line);
            try {
                if (taskType == TaskType.TODO) {
                    listOfTasks.add(new ToDo(line.substring(initialLength).trim()));
                } else {
                    String taskDetail = line.substring(initialLength, line.indexOf("(", initialLength)).trim();
                    if (taskType == TaskType.DEADLINE) {
                        description = getDeadlineDescription(line, taskDetail);
                        listOfTasks.add(new Deadline(description));
                    } else if (taskType == TaskType.EVENT) {
                        description = getEventDescription(line, taskDetail);
                        listOfTasks.add(new Event(description));
                    } else {
                        Ui.printReadFileIdentificationError();
                    }
                }
                // Marks the task as done if there's a boxed tick in the same line
                if (line.contains(Symbols.BOXED_TICK)) {
                    listOfTasks.get(taskCount).doneTask();
                }
            } catch (InvalidCommandException e) {
                Ui.printReadFileAddTaskError();
            } catch (InvalidDateException e) {
                Ui.printReadFileDateError();
            }
        }
        Ui.printDoneLoading();
    }

    /**
     * Extract the event description from the line in the file
     * to help create a new event task in ArrayList
     *
     * @param line data from the file
     * @param taskDetail detail of the event
     * @return the description formatted to be easily add into ArrayList when builder is called
     */
    public static String getEventDescription(String line, String taskDetail) {
        // Get the date indicated between "at:" and ")"
        String date = line.substring((line.indexOf("at:") + "at:".length()), line.indexOf(")")).trim();
        // Append the date onto the task detail
        return "event " + taskDetail.trim() + "/at" + date;
    }

    /**
     * Extract the deadline description from the line in the file
     * to help create a new event task in ArrayList
     *
     * @param line data from the file
     * @param taskDetail detail of the deadline
     * @return the description formatted to be easily add into ArrayList when builder is called
     */
    public static String getDeadlineDescription(String line, String taskDetail) {
        // Get the date indicated between "by:" and ")"
        String date = line.substring((line.indexOf("by:") + "by:".length()), line.indexOf(")")).trim();
        // Append the date onto the task detail
        return "deadline " + taskDetail.trim() + "/by" + date;
    }

    /**
     * Update the txt file if changes occur to the listOfTasks
     *
     * @param listOfTasks ArrayList containing list of tasks
     * @param isListModified boolean flag to detect if any changes occured to the list
     */
    public static void updateFile(ArrayList<Task> listOfTasks, boolean isListModified) {
        if (isListModified) {
            try {
                writeToFile(listOfTasks);
            } catch (IOException e) {
                Ui.printWriteFileError();
            }
        }
    }
}
