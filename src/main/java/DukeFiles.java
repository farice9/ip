import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
     * To be run at the start of program to retrieve the content from storage file
     *
     * @param listOfTasks ArrayList containing list of tasks
     * @throws IOException
     */
    public static void initializeFile(ArrayList<Task> listOfTasks) throws IOException {
        try {
            loadFileContent(listOfTasks);
        } catch (FileNotFoundException e) {
            File f = new File(dukeFilepath);
            f.createNewFile();
            System.out.println("File not found, creating one now!");
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
            int initialLength = "[T][ ]".length();
            String description;
            String date;

            try {
                if (line.startsWith("[T]")) {
                    listOfTasks.add(new ToDo(line.substring(initialLength).trim()));
                } else {
                    String taskDetail = line.substring(initialLength, line.indexOf("(", initialLength)).trim();
                    if (line.startsWith("[D]")) {
                        date = line.substring((line.indexOf("by:") + "by:".length()), line.indexOf(")")).trim();
                        description = "deadline " + taskDetail.trim() + "/by" + date;
                        listOfTasks.add(new Deadline(description));
                    } else if (line.startsWith("[E]")) {
                        date = line.substring((line.indexOf("at:") + "at:".length()), line.indexOf(")")).trim();
                        description = "event " + taskDetail.trim() + "/at" + date;
                        listOfTasks.add(new Event(description));
                    } else {
                        Ui.printReadFileIdentificationError();
                    }
                }
                if (line.contains("\u2713")) {
                    listOfTasks.get(taskCount).isDone = true;
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
     * // Update the txt file if something is added/deleted/marked as done
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
