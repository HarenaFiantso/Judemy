package judemy.fiantso.repository.UserRepository;

import judemy.fiantso.models.Users;
import judemy.fiantso.repository.JudemyRepository;
import lombok.Getter;
import org.apache.catalina.User;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
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
        String selectQuery = "SELECT * FROM users WHERE user_id = ?";
        Users user = new Users();

        try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            user = new Users(
                    resultSet.getLong("user_id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("password")
            );
            System.out.println("The data select id query is executed successfully !");
        } catch (SQLException e) {
            System.out.println("There is an error while executing the select by id query : " + e.getMessage());
        }

        return user;
    }

    @Override
    public List<Users> getAll() {
        String selectQuery = "SELECT * FROM users";
        List<Users> users = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                users.add(new Users(
                        resultSet.getLong("user_id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                ));
            }
            System.out.println("The select statement is a success ! ");
        } catch (SQLException e) {
            System.out.println("There is an error while executing the select query : " + e.getMessage());
        }

        return users;
    }

    @Override
    public void update(Users model) {

    }

    @Override
    public void delete(Long id) {

    }

}