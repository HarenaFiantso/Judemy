package judemy.fiantso.judemy.RepositoryTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import judemy.fiantso.models.Users;
import judemy.fiantso.repository.UserRepository.UserRepositoryImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryImplementationTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private UserRepositoryImplementation userRepository;

    @Test
    public void testCreateUser() throws Exception {
        Users user = new Users();
        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        Users createdUser = userRepository.create(user);

        assertEquals(user, createdUser);
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testGetUserById() throws Exception {
        Users user = new Users();
        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getLong("user_id")).thenReturn(1L);

        Users result = userRepository.getById(1L);

        assertEquals(1L, result.getUserId());
        verify(preparedStatement, times(1)).executeQuery();
    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<Users> users = new ArrayList<>();
        Users user1 = new Users();
        Users user2 = new Users();
        users.add(user1);
        users.add(user2);

        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getLong("user_id"))
                .thenReturn(1L)
                .thenReturn(2L);

        List<Users> result = userRepository.getAll();

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getUserId());
        assertEquals(2L, result.get(1).getUserId());
        verify(preparedStatement, times(1)).executeQuery();
    }

    @Test
    public void testUpdateUser() throws Exception {
        Users user = new Users();
        user.setUserId(1L);
        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        Users updatedUser = userRepository.update(user);

        assertEquals(user, updatedUser);
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testDeleteUser() throws Exception {
        long userId = 1L;
        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        userRepository.delete(userId);

        verify(preparedStatement, times(1)).executeUpdate();
    }


}
