package by.markov.databaseplugin.tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.*;
import java.util.ArrayList;

public class ReaderData extends DefaultTask {

    public static ArrayList<String> sqlArray = new ArrayList<>();

    @TaskAction
    public void readData() throws IOException {
        String dir = System.getProperty("user.dir");
        File file = new File(dir, "data.sql");

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        while (bufferedReader.ready()) {
            sqlArray.add(bufferedReader.readLine());
        }
        bufferedReader.close();
    }
}
