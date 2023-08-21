package judemy.fiantso.repository.LessonRepository;

import judemy.fiantso.models.Lessons;
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
public class LessonRepositoryImplementation implements JudemyRepository<Lessons> {
    private static final Logger logger = LoggerFactory.getLogger(LessonRepositoryImplementation.class);

    private final Connection connection;
    private static final String INSERT_QUERY = "INSERT INTO lessons (course_id, title, description, display_order) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM lessons";
    private static final String UPDATE_QUERY = "UPDATE lessons SET course_id = ?, title = ?, description = ?, display_order = ? WHERE lesson_id = ?";
    private static final String DELETE_QUERY = "DELETE FROM lessons WHERE lesson_id = ?";

    public LessonRepositoryImplementation(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Lessons create(Lessons lesson) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
            statement.setInt(1, lesson.getCourseId());
            statement.setString(2, lesson.getTitle());
            statement.setString(3, lesson.getDescription());
            statement.setInt(4, lesson.getDisplayOrder());

            statement.executeUpdate();
            logger.info("Data insert query executed successfully!");
        } catch (SQLException e) {
            logger.error("Error executing insert query: {}", e.getMessage());
        }
        return lesson;
    }

    @Override
    public Lessons getById(Long id) {
        String selectByIdQuery = "SELECT * FROM lessons WHERE lesson_id = ?";
        Lessons lesson = new Lessons();

        try (PreparedStatement statement = connection.prepareStatement(selectByIdQuery)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                lesson = new Lessons(
                        resultSet.getLong("lesson_id"),
                        resultSet.getInt("course_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getInt("display_order")
                );
            }
            logger.info("Data select by id query executed successfully!");
        } catch (SQLException e) {
            logger.error("Error executing select by id query: {}", e.getMessage());
        }

        return lesson;
    }

    @Override
    public List<Lessons> getAll() {
        List<Lessons> lessons = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                lessons.add(new Lessons(
                        resultSet.getLong("lesson_id"),
                        resultSet.getInt("course_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getInt("display_order")
                ));
            }
            logger.info("Data select query executed successfully!");
        } catch (SQLException e) {
            logger.error("Error executing select query: {}", e.getMessage());
        }

        return lessons;
    }

    @Override
    public Lessons update(Lessons lesson) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setInt(1, lesson.getCourseId());
            statement.setString(2, lesson.getTitle());
            statement.setString(3, lesson.getDescription());
            statement.setInt(4, lesson.getDisplayOrder());
            statement.setLong(5, lesson.getLessonId());

            statement.executeUpdate();
            logger.info("Data update query executed successfully!");
        } catch (SQLException e) {
            logger.error("Error executing update query: {}", e.getMessage());
        }

        return lesson;
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
