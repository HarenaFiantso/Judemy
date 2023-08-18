package judemy.fiantso.DAO.UserRepository;

import judemy.fiantso.entities.Users;

import java.util.List;

public interface UsersDAO {
    void insert(Users u);

    List<Users> findAll();

    List<Users> findById(int id);

    void delete(int id);
}
