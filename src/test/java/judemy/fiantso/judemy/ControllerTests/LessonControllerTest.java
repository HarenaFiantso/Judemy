package judemy.fiantso.judemy.ControllerTests;

import judemy.fiantso.models.Lessons;
import judemy.fiantso.service.LessonService.LessonService;
import judemy.fiantso.controller.LessonController;
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

public class LessonControllerTest {

    @Mock
    private LessonService lessonServiceMock;

    private MockMvc mockMvc;
    private LessonController lessonController;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        lessonController = new LessonController(lessonServiceMock);
        mockMvc = MockMvcBuilders.standaloneSetup(lessonController).build();
    }

    @Test
    public void testCreateLesson() throws Exception {
        Lessons lesson = new Lessons();
        when(lessonServiceMock.createLesson(any())).thenReturn(lesson);

        mockMvc.perform(post("/lessons/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))  // Utilisez ici la représentation JSON appropriée pour la création de la leçon
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.lessonId").value(lesson.getLessonId()));
    }

    @Test
    public void testGetAllLessons() throws Exception {
        List<Lessons> lessons = new ArrayList<>();
        when(lessonServiceMock.getAllLessons()).thenReturn(lessons);

        mockMvc.perform(get("/lessons/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(lessons.size()));
    }

    @Test
    public void testGetLessonById() throws Exception {
        Long lessonId = 1L;
        Lessons lesson = new Lessons();
        lesson.setLessonId(lessonId);
        when(lessonServiceMock.getLessonById(lessonId)).thenReturn(lesson);

        mockMvc.perform(get("/lessons/{lessonId}", lessonId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.lessonId").value(lessonId));
    }

    @Test
    public void testDeleteLesson() throws Exception {
        Long lessonId = 1L;

        mockMvc.perform(delete("/lessons/{lessonId}", lessonId))
                .andExpect(status().isOk());

        verify(lessonServiceMock, times(1)).deleteLesson(lessonId);
    }
}
