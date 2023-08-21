package judemy.fiantso.repository.LessonsRepository;

import judemy.fiantso.models.Lessons;
import judemy.fiantso.repository.JudemyRepository;

import java.sql.*;
import java.util.ArrayList;
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
        String selectQuery = "SELECT * FROM lessons WHERE lesson_id = ?";
        Lessons lesson = new Lessons();

        try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            lesson = new Lessons(
                    resultSet.getLong("lesson_id"),
                    resultSet.getInt("course_id"),
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getInt("display_order")
            );
            System.out.println("The data select id query is executed successfully !");
        } catch (SQLException e) {
            System.out.println("There is an error while executing the select by id query : " + e.getMessage());
        }

        return lesson;
    }

    @Override
    public List<Lessons> getAll() {
        String selectQuery = "SELECT * FROM lessons";
        List<Lessons> lessons = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                lessons.add(new Lessons(
                        resultSet.getLong("lesson_id"),
                        resultSet.getInt("course_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getInt("display_order")
                ));
            }
            System.out.println("The data select query is executed successfully !");
        } catch (SQLException e) {
            System.out.println("There is an error while executing the select query : " + e.getMessage());
        }

        return lessons;
    }

    @Override
    public Lessons update(Lessons lesson) {
        String updateQuery = "UPDATE lessons SET course_id = ?, title = ?, description = ?, display_order = ? WHERE lesson_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setInt(1, lesson.getCourseId());
            statement.setString(2, lesson.getTitle());
            statement.setString(3, lesson.getDescription());
            statement.setInt(4, lesson.getDisplayOrder());
            statement.setLong(5, lesson.getLessonId());

            statement.executeUpdate();
            System.out.println("The data update query is executed successfully !");
        } catch (SQLException e) {
            System.out.println("There is an error while executing the update query : " + e.getMessage());
        }

        return lesson;
    }

    @Override
    public void delete(Long id) {

    }
}
