package judemy.fiantso.service.userService;

import judemy.fiantso.models.Users;
import judemy.fiantso.repository.UserRepository.UsersDAO;

import java.util.List;

public class UserServiceImplementation implements UserService {
    private UsersDAO repository;

    public UserServiceImplementation(UsersDAO repository) {
        this.repository = repository;
    }

    @Override
    public List<Users> getUsers() {
        return this.repository.findAll();
    }

    @Override
    public Users getUser(int id) {
        return this.repository.findById(id);
    }

    @Override
    public Users addUser(Users u) {
        return this.repository.insert(u);
    }

    @Override
    public Users updateUser(Users u) {
        return this.repository.update(u);
    }

    @Override
    public void deleteUser(Users u) {
        this.repository.delete(u);
    }
}
