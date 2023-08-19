package judemy.fiantso.service.userService;

import judemy.fiantso.models.Users;

import java.util.List;

public interface UserService {
    Users createUser(Users user);

    List<Users> getAllUsers();

    Users getUserById(Long userId);

    Users updateUser(Users user);

    void deleteUser(Long user);
}
