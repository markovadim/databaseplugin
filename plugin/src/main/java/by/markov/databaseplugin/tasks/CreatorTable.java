package by.markov.databaseplugin.tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreatorTable extends DefaultTask {

    @TaskAction

    public void createTableInDataBase() {

        try(Connection connection = DriverManager.getConnection(Connector.URL,Connector.USER,Connector.PASSWORD)) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(ReaderSchema.sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}