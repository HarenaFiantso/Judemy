package judemy.fiantso.service.userService;

import judemy.fiantso.models.Users;

import java.util.List;

public interface UserService {
    List<Users> getUsers();
    Users getUser(int id);
    Users addUser(Users u);
    Users updateUser(Users u);

    void deleteUser(Users u);
}
