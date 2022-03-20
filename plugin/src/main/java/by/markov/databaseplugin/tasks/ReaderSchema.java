package by.markov.databaseplugin.tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.*;

public class ReaderSchema extends DefaultTask {

    public static String sqlQuery = "";

    @TaskAction
    public String readSchema() throws IOException {

        String dir = System.getProperty("user.dir");
        File file = new File(dir, "schema.sql");

//        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
//        while (bufferedReader.ready()) {
//            sqlQuery += bufferedReader.readLine();
//        }
//        bufferedReader.close();
//
//        System.out.println(sqlQuery);
//        return sqlQuery;

        Reader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        sqlQuery=br.readLine();
        br.close();
        System.out.println(sqlQuery);
        return sqlQuery;
    }
}
