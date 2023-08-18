package judemy.fiantso.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    private DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/judemy",
                    "postgres",
                    "tsy tadidiko"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            new DatabaseConnection();
        }

        return connection;
    }
}
