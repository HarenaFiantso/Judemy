package judemy.fiantso.service.userService;

import judemy.fiantso.models.Users;

import java.util.List;

public interface UserServiceImplementation {
    List<Users> getUsers();
    Users getUser(int id);
    Users addUser(Users users);
    Users updateUser(Users user);
    void deleteUser(int id);
}
