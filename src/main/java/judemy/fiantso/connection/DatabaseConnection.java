package judemy.fiantso.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    private DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(
                    Credentials.DATABASE_URL,
                    Credentials.DATABASE_USER,
                    Credentials.DATABASE_PASSWORD
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
