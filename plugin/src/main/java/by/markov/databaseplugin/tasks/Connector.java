package by.markov.databaseplugin.tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.sql.*;

public class Connector extends DefaultTask {

    public static final String URL = "jdbc:mysql://localhost:3306/testdatabase";
    public static final String USER = "root";
    public static final String PASSWORD = "root";
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";


    @TaskAction
    public void connecting() throws SQLException, ClassNotFoundException {

//        Class.forName(DRIVER);
//        try {
//            connection = DriverManager.getConnection(URL, USER, PASSWORD);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        try {
            Class.forName(DRIVER);
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                System.out.println("Connection to Store DB succesfull!");
            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");
            ex.printStackTrace();
        }
    }
}
