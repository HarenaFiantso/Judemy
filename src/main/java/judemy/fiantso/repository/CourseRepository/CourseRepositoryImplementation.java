package judemy.fiantso.repository.CourseRepository;

import judemy.fiantso.models.Courses;
import judemy.fiantso.repository.JudemyRepository;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Repository
public class CourseRepositoryImplementation implements JudemyRepository<Courses> {
    private final Connection connection;

    public CourseRepositoryImplementation(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Courses create(Courses course) {
        String insertQuery = "INSERT INTO courses (title, description, duration) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setString(1, course.getTitle());
            statement.setString(2, course.getDescription());
            statement.setInt(3, course.getDuration());

            statement.executeUpdate();
            System.out.println("The data insert query is executed successfully ! ");
        } catch (SQLException e) {
            System.out.println("There is an error while executing the insert query : " + e.getMessage());
        }
        return course;
    }

    @Override
    public Courses getById(Long id) {
        String selectQuery = "SELECT * FROM courses WHERE course_id = ?";
        Courses course = new Courses();

        try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            course = new Courses(
                    resultSet.getLong("course_id"),
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getInt("duration")
            );
            System.out.println("The data select id query is executed successfully !");
        } catch (SQLException e) {
            System.out.println("There is an error while executing the select by id query : " + e.getMessage());
        }

        return course;
    }

    @Override
    public List<Courses> getAll() {
        String selectQuery = "SELECT * FROM courses";
        List<Courses> courses = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                courses.add(new Courses(
                        resultSet.getLong("course_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getInt("duration")
                ));
            }
            System.out.println("The data select query is executed successfully !");
        } catch (SQLException e) {
            System.out.println("There is an error while executing the select query : " + e.getMessage());
        }

        return courses;
    }

    @Override
    public Courses update(Courses course) {
        String updateQuery = "UPDATE courses SET title = ?, description = ?, duration = ? WHERE course_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setString(1, course.getTitle());
            statement.setString(2, course.getDescription());
            statement.setInt(3, course.getDuration());
            statement.setLong(4, course.getCourseId());

            statement.executeUpdate();
            System.out.println("The data update query is executed successfully !");
        } catch (SQLException e) {
            System.out.println("There is an error while executing the update query : " + e.getMessage());
        }

        return course;
    }

    @Override
    public void delete(Long id) {
        String deleteQuery = "DELETE FROM courses WHERE course_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("The data delete query is executed successfully !");
        } catch (SQLException e) {
            System.out.println("There is an error while executing the delete query : " + e.getMessage());
        }
    }
}
