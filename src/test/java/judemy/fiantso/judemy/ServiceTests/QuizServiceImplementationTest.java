package judemy.fiantso.judemy.ServiceTests;

import judemy.fiantso.models.Quizzes;
import judemy.fiantso.repository.JudemyRepository;
import judemy.fiantso.service.QuizService.QuizService;
import judemy.fiantso.service.QuizService.QuizServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class QuizServiceImplementationTest {

    @Mock
    private JudemyRepository<Quizzes> quizRepositoryMock;

    private QuizService quizService;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        quizService = new QuizServiceImplementation(quizRepositoryMock);
    }

    @Test
    public void testCreateQuiz() {
        Quizzes quiz = new Quizzes();
        when(quizRepositoryMock.create(quiz)).thenReturn(quiz);

        Quizzes result = quizService.createQuiz(quiz);

        assertEquals(quiz, result);
        verify(quizRepositoryMock, times(1)).create(quiz);
    }

    @Test
    public void testGetAllQuizzes() {
        List<Quizzes> quizzes = new ArrayList<>();
        when(quizRepositoryMock.getAll()).thenReturn(quizzes);

        List<Quizzes> result = quizService.getAllQuizzes();

        assertEquals(quizzes, result);
        verify(quizRepositoryMock, times(1)).getAll();
    }

    @Test
    public void testGetQuizById() {
        Long quizId = 1L;
        Quizzes quiz = new Quizzes();
        when(quizRepositoryMock.getById(quizId)).thenReturn(quiz);

        Quizzes result = quizService.getQuizById(quizId);

        assertEquals(quiz, result);
        verify(quizRepositoryMock, times(1)).getById(quizId);
    }

    @Test
    public void testUpdateQuiz() {
        Quizzes quizToUpdate = new Quizzes();
        Quizzes updatedQuiz = new Quizzes();
        when(quizRepositoryMock.update(quizToUpdate)).thenReturn(updatedQuiz);

        Quizzes result = quizService.updateQuiz(quizToUpdate);

        assertEquals(updatedQuiz, result);
        verify(quizRepositoryMock, times(1)).update(quizToUpdate);
    }

    @Test
    public void testDeleteQuiz() {
        Long quizId = 1L;

        quizService.deleteQuiz(quizId);

        verify(quizRepositoryMock, times(1)).delete(quizId);
    }
}
