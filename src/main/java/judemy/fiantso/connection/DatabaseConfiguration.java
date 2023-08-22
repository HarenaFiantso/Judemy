package judemy.fiantso.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(Credentials.DATABASE_URL);
        config.setUsername(Credentials.DATABASE_USER);
        config.setPassword(Credentials.DATABASE_PASSWORD);

        return new HikariDataSource(config);
    }
}
