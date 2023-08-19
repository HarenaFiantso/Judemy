package judemy.fiantso.service.userService;

import judemy.fiantso.models.Users;
import judemy.fiantso.repository.JudemyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {
    private final JudemyRepository<Users> userRepository;

    public UserServiceImplementation(JudemyRepository<Users> userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Users createUser(Users user) {
        return userRepository.create(user);
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.getAll();
    }

    @Override
    public Users getUserById(Long userId) {
        return userRepository.getById(userId);
    }

    @Override
    public void updateUser(Users user) {
        userRepository.update(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.delete(userId);
    }
}
