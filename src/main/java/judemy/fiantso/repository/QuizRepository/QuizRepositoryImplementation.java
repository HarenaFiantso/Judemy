package judemy.fiantso.repository.QuizRepository;

import judemy.fiantso.models.Quizzes;
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
public class QuizRepositoryImplementation implements JudemyRepository<Quizzes> {
    private static final Logger logger = LoggerFactory.getLogger(QuizRepositoryImplementation.class);
    
    private final Connection connection;
    private static final String INSERT_QUERY = "INSERT INTO quizzes (lesson_id, question, option1, option2, option3, option4, correct_answer) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM quizzes";
    private static final String UPDATE_QUERY = "UPDATE quizzes SET lesson_id = ?, question = ?, option1 = ?, option2 = ?, option3 = ?, option4 = ?, correct_answer = ? WHERE quiz_id = ?";
    private static final String DELETE_QUERY = "DELETE FROM quizzes WHERE quiz_id = ?";

    public QuizRepositoryImplementation(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Quizzes create(Quizzes quiz) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
            setQuizParameters(statement, quiz);

            statement.executeUpdate();
            logger.info("Data insert query executed successfully!");
        } catch (SQLException e) {
            logger.error("Error executing insert query: {}", e.getMessage());
        }
        return quiz;
    }

    @Override
    public Quizzes getById(Long id) {
        String selectByIdQuery = "SELECT * FROM quizzes WHERE quiz_id = ?";
        Quizzes quiz = new Quizzes();

        try (PreparedStatement statement = connection.prepareStatement(selectByIdQuery)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
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
            }
            logger.info("Data select by id query executed successfully!");
        } catch (SQLException e) {
            logger.error("Error executing select by id query: {}", e.getMessage());
        }

        return quiz;
    }

    @Override
    public List<Quizzes> getAll() {
        List<Quizzes> quizzes = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
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
            logger.info("Data select query executed successfully!");
        } catch (SQLException e) {
            logger.error("Error executing select query: {}", e.getMessage());
        }

        return quizzes;
    }

    @Override
    public Quizzes update(Quizzes quiz) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            setQuizParameters(statement, quiz);
            statement.setLong(8, quiz.getQuizId());

            statement.executeUpdate();
            logger.info("Data update query executed successfully!");
        } catch (SQLException e) {
            logger.error("Error executing update query: {}", e.getMessage());
        }
        return quiz;
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

    private void setQuizParameters(PreparedStatement statement, Quizzes quiz) throws SQLException {
        statement.setInt(1, quiz.getLessonId());
        statement.setString(2, quiz.getQuestion());
        statement.setString(3, quiz.getOption1());
        statement.setString(4, quiz.getOption2());
        statement.setString(5, quiz.getOption3());
        statement.setString(6, quiz.getOption4());
        statement.setInt(7, quiz.getCorrectAnswer());
    }
}
