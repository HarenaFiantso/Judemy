package judemy.fiantso.repository.EnrollmentRepository;

import judemy.fiantso.models.Enrollments;
import judemy.fiantso.repository.JudemyRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Repository
public class EnrollmentRepositoryImplementation implements JudemyRepository<Enrollments> {
    private static final Logger logger = LoggerFactory.getLogger(EnrollmentRepositoryImplementation.class);

    private final Connection connection;
    private static final String INSERT_QUERY = "INSERT INTO enrollments (user_id, course_id) VALUES (?, ?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM enrollments";
    private static final String UPDATE_QUERY = "UPDATE enrollments SET user_id = ?, course_id = ?, enrollement_date = ?, status = ? WHERE enrollment_id = ?";
    private static final String DELETE_QUERY = "DELETE FROM enrollments WHERE enrollment_id = ?";

    public EnrollmentRepositoryImplementation(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Enrollments create(Enrollments enrollment) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
            statement.setInt(1, enrollment.getUserId());
            statement.setInt(2, enrollment.getCourseId());

            statement.executeUpdate();
            logger.info("Data insert query executed successfully!");
        } catch (SQLException e) {
            logger.error("Error executing insert query: {}", e.getMessage());
        }

        return enrollment;
    }

    @Override
    public Enrollments getById(Long id) {
        String selectByIdQuery = "SELECT * FROM enrollments WHERE enrollment_id = ?";
        Enrollments enrollment = new Enrollments();

        try (PreparedStatement statement = connection.prepareStatement(selectByIdQuery)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                enrollment = new Enrollments(
                        resultSet.getLong("enrollment_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("course_id"),
                        resultSet.getDate("enrollment_date"),
                        resultSet.getString("status")
                );
            }
            logger.info("Data select by id query executed successfully!");
        } catch (SQLException e) {
            logger.error("Error executing select by id query: {}", e.getMessage());
        }

        return enrollment;
    }

    @Override
    public List<Enrollments> getAll() {
        List<Enrollments> enrollments = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                enrollments.add(new Enrollments(
                        resultSet.getLong("enrollment_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("course_id"),
                        resultSet.getDate("enrollment_date"),
                        resultSet.getString("status")
                ));
            }
            logger.info("Data select query executed successfully!");
        } catch (SQLException e) {
            logger.error("Error executing select query: {}", e.getMessage());
        }

        return enrollments;
    }

    @Override
    public Enrollments update(Enrollments enrollment) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setInt(1, enrollment.getUserId());
            statement.setInt(2, enrollment.getCourseId());
            statement.setDate(3, enrollment.getEnrollmentDate());
            statement.setString(4, enrollment.getStatus());
            statement.setLong(5, enrollment.getEnrollmentId());

            statement.executeUpdate();
            logger.info("Data update query executed successfully!");
        } catch (SQLException e) {
            logger.error("Error executing update query: {}", e.getMessage());
        }

        return enrollment;
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            logger.info("Data delete query executed successfully!");
        } catch (SQLException e) {
            logger.error("Error executing delete query: {}", e.getMessage());
        }
    }
}
