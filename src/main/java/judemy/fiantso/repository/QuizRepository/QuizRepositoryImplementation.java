package judemy.fiantso.repository.QuizRepository;

import judemy.fiantso.models.Quizzes;
import judemy.fiantso.repository.JudemyRepository;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Repository
public class QuizRepositoryImplementation implements JudemyRepository<Quizzes> {
    private final Connection connection;

    public QuizRepositoryImplementation(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Quizzes create(Quizzes quiz) {
        String insertQuery = "INSERT INTO quizzes (lesson_id, question, option1, option2, option3, option4, correct_answer) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement(quiz, statement);

            statement.executeUpdate();
            System.out.println("The data insert query is executed successfully ! ");
        } catch (SQLException e) {
            System.out.println("There is an error while executing the insert query : " + e.getMessage());
        }
        return quiz;
    }

    @Override
    public Quizzes getById(Long id) {
        String selectQuery = "SELECT * FROM quizzes WHERE quiz_id = ?";
        Quizzes quiz = new Quizzes();

        try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            quiz = new Quizzes(
                    resultSet.getLong("quiz_id"),
                    resultSet.getInt("lesson_id"),
                    resultSet.getString("question"),
                    resultSet.getString("option1"),
                    resultSet.getString("option2"),
                    resultSet.getString("option3"),
                    resultSet.getString("option4"),
                    resultSet.getInt("correct_answer")
            );
            System.out.println("The data select id query is executed successfully !");
        } catch (SQLException e) {
            System.out.println("There is an error while executing the select by id query : " + e.getMessage());
        }

        return quiz;
    }

    @Override
    public List<Quizzes> getAll() {
        String selectQuery = "SELECT * FROM quizzes";
        List<Quizzes> quizzes = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                quizzes.add(new Quizzes(
                        resultSet.getLong("quiz_id"),
                        resultSet.getInt("lesson_id"),
                        resultSet.getString("question"),
                        resultSet.getString("option1"),
                        resultSet.getString("option2"),
                        resultSet.getString("option3"),
                        resultSet.getString("option4"),
                        resultSet.getInt("correct_answer")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return quizzes;
    }

    @Override
    public Quizzes update(Quizzes quiz) {
        String updateQuery = "UPDATE quizzes SET lesson_id = ?, question = ?, option1 = ?, option2 = ?, option3 = ?, option4 = ?, correct_answer = ? WHERE quiz_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement(quiz, statement);
            statement.setLong(8, quiz.getQuizId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return quiz;
    }

    @Override
    public void delete(Long id) {
        String deleteQuery = "DELETE FROM quizzes WHERE quiz_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("The data delete query is executed successfully !");
        } catch (SQLException e) {
            System.out.println("There is an error while executing the delete query : " + e.getMessage());
        }
    }

    private void statement(Quizzes quiz, PreparedStatement statement) throws SQLException {
        statement.setInt(1, quiz.getLessonId());
        statement.setString(2, quiz.getQuestion());
        statement.setString(3, quiz.getOption1());
        statement.setString(4, quiz.getOption2());
        statement.setString(5, quiz.getOption3());
        statement.setString(6, quiz.getOption4());
        statement.setInt(7, quiz.getCorrectAnswer());
    }
}
