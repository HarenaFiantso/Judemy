package judemy.fiantso.repository.UserRepository;

import judemy.fiantso.connection.DatabaseConnection;
import judemy.fiantso.models.Users;

import java.sql.Connection;
import java.sql.SQLException;

public class UserRepositoryTest {
    public static void main(String[] args) {
        try {
            Connection connection = DatabaseConnection.getConnection();

            UserRepositoryImplementation userRepository = new UserRepositoryImplementation(connection);

            Users user = new Users();
            user.setName("John Doe");
            user.setEmail("john@example.com");
            user.setPassword("secret123");

            userRepository.create(user);

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
