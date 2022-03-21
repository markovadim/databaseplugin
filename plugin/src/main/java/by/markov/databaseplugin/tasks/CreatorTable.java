package by.markov.databaseplugin.tasks;

import by.markov.databaseplugin.SQLProperty;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreatorTable extends DefaultTask {

    @TaskAction

    public void createTableInDataBase() {
        try {
            Class.forName(SQLProperty.DRIVER);
            try (Connection connection = DriverManager.getConnection(SQLProperty.URL, SQLProperty.USER, SQLProperty.PASSWORD)) {
                Statement statement = connection.createStatement();
                statement.executeUpdate(ReaderSchema.sqlQuery);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}