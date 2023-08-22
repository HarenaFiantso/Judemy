package judemy.fiantso.judemy.ControllerTests;

import judemy.fiantso.models.Users;
import judemy.fiantso.service.userService.UserService;
import judemy.fiantso.controller.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest {

    @Mock
    private UserService userServiceMock;

    private MockMvc mockMvc;
    private UserController userController;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        userController = new UserController(userServiceMock);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testCreateUser() throws Exception {
        Users user = new Users();
        when(userServiceMock.createUser(any())).thenReturn(user);

        mockMvc.perform(post("/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))  // Utilisez ici la représentation JSON appropriée pour la création de l'utilisateur
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userId").value(user.getUserId()));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<Users> users = new ArrayList<>();
        when(userServiceMock.getAllUsers()).thenReturn(users);

        mockMvc.perform(get("/users/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(users.size()));
    }

    @Test
    public void testGetUserById() throws Exception {
        Long userId = 1L;
        Users user = new Users();
        user.setUserId(userId);

        when(userServiceMock.getUserById(userId)).thenReturn(user);

        mockMvc.perform(get("/users/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userId").value(userId));
    }

    @Test
    public void testDeleteUser() throws Exception {
        Long userId = 1L;

        mockMvc.perform(delete("/users/{userId}", userId))
                .andExpect(status().isOk());

        verify(userServiceMock, times(1)).deleteUser(userId);
    }
}
