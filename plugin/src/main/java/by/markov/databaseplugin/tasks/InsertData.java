package by.markov.databaseplugin.tasks;

import by.markov.databaseplugin.SQLProperty;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertData extends DefaultTask {

    @TaskAction
    public void addDataToBase(){
        try(Connection connection = DriverManager.getConnection(SQLProperty.URL,SQLProperty.USER,SQLProperty.PASSWORD)) {
            Statement statement = connection.createStatement();
            for (String line : ReaderData.sqlArray){
                statement.executeUpdate(line);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
