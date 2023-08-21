package judemy.fiantso.repository.CourseRepository;

import judemy.fiantso.models.Courses;
import judemy.fiantso.repository.JudemyRepository;
import lombok.Getter;
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
public class CourseRepositoryImplementation implements JudemyRepository<Courses> {
    private static final Logger logger = LoggerFactory.getLogger(CourseRepositoryImplementation.class);

    private final Connection connection;
    private static final String INSERT_QUERY = "INSERT INTO courses (title, description, duration) VALUES (?, ?, ?)";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM courses WHERE course_id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM courses";
    private static final String UPDATE_QUERY = "UPDATE courses SET title = ?, description = ?, duration = ? WHERE course_id = ?";
    private static final String DELETE_QUERY = "DELETE FROM courses WHERE course_id = ?";

    public CourseRepositoryImplementation(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Courses create(Courses course) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
            statement.setString(1, course.getTitle());
            statement.setString(2, course.getDescription());
            statement.setInt(3, course.getDuration());

            statement.executeUpdate();
            logger.info("Data insert query executed successfully!");
        } catch (SQLException e) {
            logger.error("Error executing insert query: {}", e.getMessage());
        }
        return course;
    }

    @Override
    public Courses getById(Long id) {
        Courses course = new Courses();

        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                course = new Courses(
                        resultSet.getLong("course_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getInt("duration")
                );
            }
            logger.info("Data select by id query executed successfully!");
        } catch (SQLException e) {
            logger.error("Error executing select by id query: {}", e.getMessage());
        }

        return course;
    }

    @Override
    public List<Courses> getAll() {
        List<Courses> courses = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                courses.add(new Courses(
                        resultSet.getLong("course_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getInt("duration")
                ));
            }
            logger.info("Data select query executed successfully!");
        } catch (SQLException e) {
            logger.error("Error executing select query: {}", e.getMessage());
        }

        return courses;
    }

    @Override
    public Courses update(Courses course) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, course.getTitle());
            statement.setString(2, course.getDescription());
            statement.setInt(3, course.getDuration());
            statement.setLong(4, course.getCourseId());

            statement.executeUpdate();
            logger.info("Data update query executed successfully!");
        } catch (SQLException e) {
            logger.error("Error executing update query: {}", e.getMessage());
        }

        return course;
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
