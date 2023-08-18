package judemy.fiantso.repository.UserRepository;

import judemy.fiantso.models.Users;

import java.util.List;

public interface UsersDAO {
    void insert(Users u);

    List<Users> findAll();

    Users findById(int id);

    void delete(int id);
}
