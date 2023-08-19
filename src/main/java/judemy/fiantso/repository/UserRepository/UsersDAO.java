package judemy.fiantso.repository.UserRepository;

import judemy.fiantso.models.Users;

import java.util.List;

public interface UsersDAO {
    Users insert(Users u);

    List<Users> findAll();

    Users findById(int u);

    Users update(Users u);

    void delete(Users u);
}
