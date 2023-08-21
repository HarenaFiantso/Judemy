package judemy.fiantso.repository.LessonsRepository;

import judemy.fiantso.models.Lessons;
import judemy.fiantso.repository.JudemyRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class LessonRepositoryImplementation implements JudemyRepository<Lessons> {
    private final Connection connection;

    public LessonRepositoryImplementation(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Lessons create(Lessons lesson) {
        String insertQuery = "INSERT INTO lessons (course_id, title, description, display_order) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setInt(1, lesson.getCourseId());
            statement.setString(2, lesson.getTitle());
            statement.setString(3, lesson.getDescription());
            statement.setInt(4, lesson.getDisplayOrder());

            statement.executeUpdate();
            System.out.println("The data insert query is executed successfully ! ");
        } catch (SQLException e) {
            System.out.println("There is an error while executing the insert query : " + e.getMessage());
        }
        return lesson;
    }

    @Override
    public Lessons getById(Long id) {
        return null;
    }

    @Override
    public List<Lessons> getAll() {
        return null;
    }

    @Override
    public Lessons update(Lessons model) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
