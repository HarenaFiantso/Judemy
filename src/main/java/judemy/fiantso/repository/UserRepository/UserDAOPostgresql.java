package judemy.fiantso.repository.UserRepository;

import judemy.fiantso.connection.DatabaseConnection;
import judemy.fiantso.models.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOPostgresql implements UsersDAO {
    private Connection connection;

    public UserDAOPostgresql(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Users u) {
        String insertQuery = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setString(1, u.getName());
            statement.setString(2, u.getEmail());
            statement.setString(3, u.getPassword());

            statement.executeUpdate();
            System.out.println("The data insert query is executed successfully ! ");
        } catch (SQLException e) {
            System.out.println("There is an error while executing the insert query : " + e.getMessage());
        }
    }

    @Override
    public List<Users> findAll() {
        String selectQuery = "SELECT * FROM users";
        List<Users> users = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                users.add(new Users(
                        resultSet.getInt("user_id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password")));
            }
        } catch (SQLException e) {
            System.out.println("There is an error while executing the select query : " + e.getMessage());
        }

        return users;
    }

    @Override
    public Users findById(int id) {
        String selectQuery = "SELECT * FROM users WHERE user_id = ?";
        Users user = new Users();

        try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("The data select query is executed successfully !");
        } catch (SQLException e) {
            System.out.println("There is an error while executing the select query : " + e.getMessage());
        }

        return user;
    }

    @Override
    public void delete(int id) {
        String deleteQuery = "DELETE FROM users WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("The data delete query is executed successfully !");
        } catch (SQLException e) {
            System.out.println("There is an error while executing the delete query : " + e.getMessage());
        }
    }
}
