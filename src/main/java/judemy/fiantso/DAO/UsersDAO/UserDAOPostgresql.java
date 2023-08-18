package judemy.fiantso.DAO.UsersDAO;

import judemy.fiantso.connection.DatabaseConnection;
import judemy.fiantso.entities.Users;

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
    public List<Users> findById(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    public static void main(String[] args) throws SQLException {
        Users fiantso = new Users(
                1,
                "Fiantso",
                "hei.fiantso@gmail.com",
                "1234");
        UsersDAO dao = new UserDAOPostgresql(DatabaseConnection.getConnection());
        System.out.println(dao.findAll());
    }
}
