package judemy.fiantso.repository.EnrollmentRepository;

import judemy.fiantso.models.Enrollments;
import judemy.fiantso.repository.JudemyRepository;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Repository
public class EnrollmentRepositoryImplementation implements JudemyRepository<Enrollments> {
    private final Connection connection;

    public EnrollmentRepositoryImplementation(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Enrollments create(Enrollments enrollment) {
        String insertQuery = "INSERT INTO enrollments (user_id, course_id) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setInt(1, enrollment.getUserId());
            statement.setInt(2, enrollment.getCourseId());

            statement.executeUpdate();
            System.out.println("The data insert query is executed successfully ! ");
        } catch (SQLException e) {
            System.out.println("There is an error while executing the insert query : " + e.getMessage());
        }

        return enrollment;
    }

    @Override
    public Enrollments getById(Long id) {
        return null;
    }

    @Override
    public List<Enrollments> getAll() {
        String selectQuery = "SELECT * FROM enrollments";
        List<Enrollments> enrollments = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                enrollments.add(new Enrollments(
                        resultSet.getLong("enrollment_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("course_id"),
                        resultSet.getDate("enrollement_date"),
                        resultSet.getString("status")
                ));
            }
            System.out.println("The data select query is executed successfully ! ");
        } catch (SQLException e) {
            System.out.println("There is an error while executing the select query : " + e.getMessage());
        }

        return enrollments;
    }

    @Override
    public Enrollments update(Enrollments enrollment) {
        String updateQuery = "UPDATE enrollments SET user_id = ?, course_id = ?, enrollement_date = ?, status = ? WHERE enrollment_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setInt(1, enrollment.getUserId());
            statement.setInt(2, enrollment.getCourseId());
            statement.setDate(3, enrollment.getEnrollmentDate());
            statement.setString(4, enrollment.getStatus());
            statement.setLong(5, enrollment.getEnrollmentId());

            statement.executeUpdate();
            System.out.println("The data update query is executed successfully !");
        } catch (SQLException e) {
            System.out.println("There is an error while executing the update query : " + e.getMessage());
        }

        return enrollment;
    }

    @Override
    public void delete(Long id) {
        String deleteQuery = "DELETE FROM enrollments WHERE enrollment_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("The data delete query is executed successfully !");
        } catch (SQLException e) {
            System.out.println("There is an error while executing the delete query : " + e.getMessage());
        }
    }
}
