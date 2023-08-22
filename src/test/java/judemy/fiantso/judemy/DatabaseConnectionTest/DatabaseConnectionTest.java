package judemy.fiantso.judemy.DatabaseConnectionTest;

import com.zaxxer.hikari.HikariDataSource;
import judemy.fiantso.connection.DatabaseConnection;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class DatabaseConnectionTest {

    @Test
    public void testGetConnection() throws SQLException {
        HikariDataSource mockDataSource = mock(HikariDataSource.class);

        Connection mockedConnection = mock(Connection.class);
        when(mockDataSource.getConnection()).thenReturn(mockedConnection);

        DatabaseConnection databaseConnection = new DatabaseConnection(mockDataSource);
        Connection connection = databaseConnection.getConnection();
        verify(mockDataSource, times(1)).getConnection();
        assertEquals(mockedConnection, connection);
    }
}
