import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DukeFiles {
    private static final String dukeFilepath = "tasks.txt";

    public static void writeToFile(Task[] listOfTasks) throws IOException {
        FileWriter fw = new FileWriter(dukeFilepath);

        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            fw.write(listOfTasks[i].toString() + System.lineSeparator());
        }
        fw.close();
    }

    public static void initializeFile(Task[] listOfTasks) throws IOException {
        try {
            loadFileContent(listOfTasks);
        } catch (FileNotFoundException e) {
            File f = new File(dukeFilepath);
            f.createNewFile();
            System.out.println("File not found, creating one now!");
        }
    }

    public static void loadFileContent(Task[] listOfTasks) throws FileNotFoundException {
        File f = new File(dukeFilepath);
        Scanner s = new Scanner(f);

        System.out.println("Loading your previous tasks, hold on! ...");
        while (s.hasNext()) {
            String line = s.nextLine();

            int taskCount = Task.getNumberOfTasks();
            int initialLength = "[T][ ]".length();
            String description;
            String date;

            try {
                if (line.startsWith("[T]")) {
                    listOfTasks[taskCount] = new ToDo(line.substring(initialLength).trim());
                } else {
                    String taskDetail = line.substring(initialLength, line.indexOf("(", initialLength)).trim();
                    if (line.startsWith("[D]")) {
                        date = line.substring((line.indexOf("by:") + "by:".length()), line.indexOf(")")).trim();
                        description = "deadline " + taskDetail.trim() + "/by" + date;
                        listOfTasks[taskCount] = new Deadline(description);
                    } else if (line.startsWith("[E]")) {
                        date = line.substring((line.indexOf("at:") + "at:".length()), line.indexOf(")")).trim();
                        description = "event " + taskDetail.trim() + "/at" + date;
                        listOfTasks[taskCount] = new Event(description);
                    } else {
                        System.out.println("There's a problem identifying this task while reading file");
                    }
                }
                if (line.contains("\u2713")) {
                    listOfTasks[taskCount].isDone = true;
                }
            } catch (InvalidCommandException e) {
                System.out.println("Problem adding this task from storage file");
            } catch (InvalidDateException e) {
                System.out.println("Problem adding task due to incorrect date input");
            }
        }
        System.out.println("... Storage file content loaded!");
    }
}
