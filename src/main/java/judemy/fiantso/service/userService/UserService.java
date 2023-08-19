package judemy.fiantso.service.userService;

import judemy.fiantso.models.Users;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    Users createUser(Users user);

    List<Users> getAllUsers();

    Users getUserById(Long userId);

    ResponseEntity<Users> updateUser(Users user);

    void deleteUser(Long userId);
}
