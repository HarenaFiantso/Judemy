package judemy.fiantso.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {
    private static HikariDataSource dataSource;

    private DatabaseConnection() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(Credentials.DATABASE_URL);
        config.setUsername(Credentials.DATABASE_USER);
        config.setPassword(Credentials.DATABASE_PASSWORD);

        dataSource = new HikariDataSource(config);

        System.out.println("Connection pool initialized !");
    }

    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            new DatabaseConnection();
        }
        return dataSource.getConnection();
    }

    public static void main(String[] args) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();

        connection.close();
        dataSource.close();
    }
}
