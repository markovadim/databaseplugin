package by.markov.databaseplugin.tasks;

import by.markov.databaseplugin.SQLProperty;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Uninstaller extends DefaultTask {

    private static String DROP_TABLE = "DROP TABLE clients";

    @TaskAction
    public void dropTable() {
        try (Connection connection = DriverManager.getConnection(SQLProperty.URL, SQLProperty.USER, SQLProperty.PASSWORD)) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(DROP_TABLE);
            System.out.println("The table is dropping");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("The table is not exist");
        }
    }
}
