package judemy.fiantso.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class DatabaseConnection {
    private static HikariDataSource dataSource;

    private DatabaseConnection() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(Credentials.DATABASE_URL);
        config.setUsername(Credentials.DATABASE_USER);
        config.setPassword(Credentials.DATABASE_PASSWORD);

        dataSource = new HikariDataSource(config);
    }

    @Bean
    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            new DatabaseConnection();
        }
        return dataSource.getConnection();
    }
}
