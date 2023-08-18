package judemy.fiantso.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    private DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(Credentials.DATABASE_URL, Credentials.DATABASE_USER, Credentials.DATABASE_PASSWORD);
            System.out.println("Connected successfully !");
        } catch (SQLException e) {
            System.out.println("There is an error while connecting to the database !" + e.getMessage());
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            new DatabaseConnection();
        }
        return connection;
    }

    public static void main(String[] args) {
        DatabaseConnection.getConnection();
    }
}
