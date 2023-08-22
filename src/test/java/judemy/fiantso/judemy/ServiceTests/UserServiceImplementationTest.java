package judemy.fiantso.judemy.ServiceTests;

import judemy.fiantso.models.Users;
import judemy.fiantso.repository.JudemyRepository;
import judemy.fiantso.service.userService.UserServiceImplementation;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceImplementationTest {

    private UserServiceImplementation userService;
    private JudemyRepository<Users> userRepositoryMock;

    @Before
    public void setUp() {
        userRepositoryMock = mock(JudemyRepository.class);
        userService = new UserServiceImplementation(userRepositoryMock);
    }

    @Test
    public void testCreateUser() {
        Users userToCreate = new Users();
        Users createdUser = new Users();
        when(userRepositoryMock.create(userToCreate)).thenReturn(createdUser);

        Users result = userService.createUser(userToCreate);

        assertEquals(createdUser, result);
        verify(userRepositoryMock, times(1)).create(userToCreate);
    }

    @Test
    public void testGetAllUsers() {
        List<Users> userList = new ArrayList<>();
        when(userRepositoryMock.getAll()).thenReturn(userList);

        List<Users> result = userService.getAllUsers();

        assertEquals(userList, result);
        verify(userRepositoryMock, times(1)).getAll();
    }

    @Test
    public void testGetUserById() {
        Long userId = 1L;
        Users user = new Users();
        when(userRepositoryMock.getById(userId)).thenReturn(user);

        Users result = userService.getUserById(userId);

        assertEquals(user, result);
        verify(userRepositoryMock, times(1)).getById(userId);
    }


    @Test
    public void testUpdateUser() {
        Users userToUpdate = new Users();
        Users updatedUser = new Users();

        when(userRepositoryMock.update(userToUpdate)).thenReturn(updatedUser);

        Users result = userService.updateUser(userToUpdate);
        assertEquals(updatedUser, result);
        verify(userRepositoryMock, times(1)).update(userToUpdate);
    }


    @Test
    public void testDeleteUser() {
        Long userId = 1L;

        userService.deleteUser(userId);

        verify(userRepositoryMock, times(1)).delete(userId);
    }
}
