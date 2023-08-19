package judemy.fiantso.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnectionTest {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                System.out.println("Connected to the database!");

                /* A simple SQL test */
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT 1");
                while (resultSet.next()) {
                    int result = resultSet.getInt(1);
                    System.out.println("Result: " + result);
                }
            } else {
                System.out.println("Failed to connect to the database!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
