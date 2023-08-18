package judemy.fiantso.DAO.UsersDAO;

import judemy.fiantso.entities.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
            System.out.println("The data is inserted successfully ! ");
        } catch (SQLException e) {
            System.out.println("There is an error while trying to insert the data : " + e.getMessage());
        }
    }

    @Override
    public List<Users> findAll() {
        return null;
    }

    @Override
    public List<Users> findById(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
