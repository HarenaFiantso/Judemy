package judemy.fiantso.judemy.ControllerTests;

import judemy.fiantso.models.Quizzes;
import judemy.fiantso.service.QuizService.QuizService;
import judemy.fiantso.controller.QuizController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class QuizControllerTest {

    @Mock
    private QuizService quizServiceMock;

    private MockMvc mockMvc;
    private QuizController quizController;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        quizController = new QuizController(quizServiceMock);
        mockMvc = MockMvcBuilders.standaloneSetup(quizController).build();
    }

    @Test
    public void testGetAllQuizzes() throws Exception {
        List<Quizzes> quizzes = new ArrayList<>();
        when(quizServiceMock.getAllQuizzes()).thenReturn(quizzes);

        mockMvc.perform(get("/quizzes/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(quizzes.size()));
    }

    @Test
    public void testDeleteQuiz() throws Exception {
        Long quizId = 1L;

        mockMvc.perform(delete("/quizzes/{quizId}", quizId))
                .andExpect(status().isOk());

        verify(quizServiceMock, times(1)).deleteQuiz(quizId);
    }
}
