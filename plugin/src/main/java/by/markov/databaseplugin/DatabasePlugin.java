package by.markov.databaseplugin;

import by.markov.databaseplugin.tasks.*;
import org.gradle.api.Project;
import org.gradle.api.Plugin;

import java.io.IOException;

public class DatabasePlugin implements Plugin<Project> {

    public void apply(Project project) {
        // Register a task
        project.getTasks().register("greeting", task -> {
            task.doLast(s -> System.out.println("Hello from plugin 'databaseplugin.greeting'"));
        });

        project.getTasks().register("readSchema", ReaderSchema.class, task -> {
            task.setGroup("database");
            try {
                task.readSchema();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        project.getTasks().register("createTable", CreatorTable.class, task -> {
            task.dependsOn("readSchema");
            task.setGroup("database");
            task.createTableInDataBase();
        });

        project.getTasks().register("readData", ReaderData.class, task -> {
            task.dependsOn("createTable");
            task.setGroup("database");
            try {
                task.readData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        project.getTasks().register("insertData", InsertData.class, task -> {
            task.dependsOn("readData");
            task.setGroup("database");
            task.addDataToBase();
        });

        project.getTasks().register("dropTable", Uninstaller.class, task -> {
            task.setGroup("database");
            task.dropTable();
        });

    }
}
