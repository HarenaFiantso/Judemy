package judemy.fiantso.judemy.RepositoryTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import judemy.fiantso.models.Quizzes;
import judemy.fiantso.repository.QuizRepository.QuizRepositoryImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuizRepositoryImplementationTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    private QuizRepositoryImplementation quizRepository;

    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.openMocks(this);
        quizRepository = new QuizRepositoryImplementation(connection);
    }

    @Test
    public void testCreateQuiz() throws Exception {
        Quizzes quiz = new Quizzes();
        quiz.setLessonId(1); // Set the lesson ID
        quiz.setQuestion("What is the capital of France?");
        quiz.setOption1("Paris");
        quiz.setOption2("London");
        quiz.setOption3("Berlin");
        quiz.setOption4("Madrid");
        quiz.setCorrectAnswer(1); // Set the correct answer

        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        Quizzes createdQuiz = quizRepository.create(quiz);

        assertNotNull(createdQuiz);
        assertEquals(quiz, createdQuiz);
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testGetQuizById() throws Exception {
        Long quizId = 1L;
        Quizzes expectedQuiz = new Quizzes();
        expectedQuiz.setQuizId(quizId);
        expectedQuiz.setLessonId(1);
        expectedQuiz.setQuestion("What is a quiz?");
        expectedQuiz.setOption1("Option A");
        expectedQuiz.setOption2("Option B");
        expectedQuiz.setOption3("Option C");
        expectedQuiz.setOption4("Option D");
        expectedQuiz.setCorrectAnswer(2);

        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getLong("quiz_id")).thenReturn(quizId);
        when(resultSet.getInt("lesson_id")).thenReturn(1);
        when(resultSet.getString("question")).thenReturn("What is a quiz?");
        when(resultSet.getString("option1")).thenReturn("Option A");
        when(resultSet.getString("option2")).thenReturn("Option B");
        when(resultSet.getString("option3")).thenReturn("Option C");
        when(resultSet.getString("option4")).thenReturn("Option D");
        when(resultSet.getInt("correct_answer")).thenReturn(2);

        Quizzes result = quizRepository.getById(quizId);

        assertNotNull(result);
        assertEquals(expectedQuiz, result);
        verify(preparedStatement, times(1)).executeQuery();
    }


    @Test
    public void testGetAllQuizzes() throws Exception {
        List<Quizzes> expectedQuizzes = new ArrayList<>();
        Quizzes quiz1 = new Quizzes();
        quiz1.setQuizId(1L);
        quiz1.setLessonId(1);
        quiz1.setQuestion("Question 1");
        quiz1.setOption1("Option A1");
        quiz1.setOption2("Option B1");
        quiz1.setOption3("Option C1");
        quiz1.setOption4("Option D1");
        quiz1.setCorrectAnswer(1);
        expectedQuizzes.add(quiz1);

        Quizzes quiz2 = new Quizzes();
        quiz2.setQuizId(2L);
        quiz2.setLessonId(2);
        quiz2.setQuestion("Question 2");
        quiz2.setOption1("Option A2");
        quiz2.setOption2("Option B2");
        quiz2.setOption3("Option C2");
        quiz2.setOption4("Option D2");
        quiz2.setCorrectAnswer(2);
        expectedQuizzes.add(quiz2);

        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getLong("quiz_id")).thenReturn(1L, 2L);
        when(resultSet.getInt("lesson_id")).thenReturn(1, 2);
        when(resultSet.getString("question")).thenReturn("Question 1", "Question 2");
        when(resultSet.getString("option1")).thenReturn("Option A1", "Option A2");
        when(resultSet.getString("option2")).thenReturn("Option B1", "Option B2");
        when(resultSet.getString("option3")).thenReturn("Option C1", "Option C2");
        when(resultSet.getString("option4")).thenReturn("Option D1", "Option D2");
        when(resultSet.getInt("correct_answer")).thenReturn(1, 2);

        List<Quizzes> result = quizRepository.getAll();

        assertEquals(expectedQuizzes.size(), result.size());
        assertEquals(expectedQuizzes.get(0), result.get(0));
        assertEquals(expectedQuizzes.get(1), result.get(1));
        verify(preparedStatement, times(1)).executeQuery();
    }


    @Test
    public void testUpdateQuiz() throws Exception {
        Quizzes quiz = new Quizzes();
        quiz.setQuizId(1L);
        quiz.setLessonId(1);
        quiz.setQuestion("Updated Question");
        quiz.setOption1("Updated Option A");
        quiz.setOption2("Updated Option B");
        quiz.setOption3("Updated Option C");
        quiz.setOption4("Updated Option D");
        quiz.setCorrectAnswer(3);

        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        Quizzes updatedQuiz = quizRepository.update(quiz);

        assertNotNull(updatedQuiz);
        assertEquals(quiz, updatedQuiz);
        verify(preparedStatement, times(1)).executeUpdate();
    }


    @Test
    public void testDeleteQuiz() throws Exception {
        Long quizId = 1L;
        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        quizRepository.delete(quizId);

        verify(preparedStatement, times(1)).executeUpdate();
    }
}
