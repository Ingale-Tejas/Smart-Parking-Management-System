package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/smart_parking_system";
    private static final String USER = "root";
    private static final String PASSWORD = "Roots@123";

    public static Connection getConnection() {
        try{
            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Datbases Connected Successfully");
            return connection;
        } catch (SQLException e){
            System.out.println("Database Connection Failed");
            e.printStackTrace();

            return null;
        }
    }
}
