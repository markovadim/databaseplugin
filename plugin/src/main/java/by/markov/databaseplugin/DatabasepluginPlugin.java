package by.markov.databaseplugin;

import by.markov.databaseplugin.tasks.Connector;
import by.markov.databaseplugin.tasks.CreatorTable;
import by.markov.databaseplugin.tasks.ReaderSchema;
import org.gradle.api.Project;
import org.gradle.api.Plugin;

import java.io.IOException;
import java.sql.SQLException;

public class DatabasepluginPlugin implements Plugin<Project> {

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

        project.getTasks().register("createConnecting", Connector.class, task -> {
            task.dependsOn("readSchema");
            task.setGroup("database");
            try {
                task.connecting();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        project.getTasks().register("createTable", CreatorTable.class, task -> {
            task.dependsOn("createConnecting");
            task.setGroup("database");
            task.createTableInDataBase();
        });

    }
}
