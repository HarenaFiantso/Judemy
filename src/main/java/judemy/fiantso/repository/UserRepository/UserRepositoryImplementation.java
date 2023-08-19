package judemy.fiantso.repository.UserRepository;

import judemy.fiantso.models.Users;
import judemy.fiantso.repository.JudemyRepository;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Getter
@Repository
public class UserRepositoryImplementation implements JudemyRepository<Users> {
    private final Connection connection;

    public UserRepositoryImplementation(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Users create(Users user) {
        String insertQuery = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());

            statement.executeUpdate();
            System.out.println("The data insert query is executed successfully ! ");
        } catch (SQLException e) {
            System.out.println("There is an error while executing the insert query : " + e.getMessage());
        }
        return user;
    }

    @Override
    public Users getById(Long id) {
        return null;
    }

    @Override
    public List<Users> getAll() {
        return null;
    }

    @Override
    public void update(Users model) {

    }

    @Override
    public void delete(Long id) {

    }

}