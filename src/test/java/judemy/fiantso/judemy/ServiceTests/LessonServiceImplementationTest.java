package judemy.fiantso.judemy.ServiceTests;

import judemy.fiantso.models.Lessons;
import judemy.fiantso.repository.JudemyRepository;
import judemy.fiantso.service.LessonService.LessonService;
import judemy.fiantso.service.LessonService.LessonServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class LessonServiceImplementationTest {

    @Mock
    private JudemyRepository<Lessons> lessonRepositoryMock;

    private LessonService lessonService;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        lessonService = new LessonServiceImplementation(lessonRepositoryMock);
    }

    @Test
    public void testCreateLesson() {
        Lessons lesson = new Lessons();
        when(lessonRepositoryMock.create(lesson)).thenReturn(lesson);

        Lessons result = lessonService.createLesson(lesson);

        assertEquals(lesson, result);
        verify(lessonRepositoryMock, times(1)).create(lesson);
    }

    @Test
    public void testGetAllLessons() {
        List<Lessons> lessons = new ArrayList<>();
        when(lessonRepositoryMock.getAll()).thenReturn(lessons);

        List<Lessons> result = lessonService.getAllLessons();

        assertEquals(lessons, result);
        verify(lessonRepositoryMock, times(1)).getAll();
    }

    @Test
    public void testGetLessonById() {
        Long lessonId = 1L;
        Lessons lesson = new Lessons();
        when(lessonRepositoryMock.getById(lessonId)).thenReturn(lesson);

        Lessons result = lessonService.getLessonById(lessonId);

        assertEquals(lesson, result);
        verify(lessonRepositoryMock, times(1)).getById(lessonId);
    }

    @Test
    public void testUpdateLesson() {
        Lessons lessonToUpdate = new Lessons();
        Lessons updatedLesson = new Lessons();
        when(lessonRepositoryMock.update(lessonToUpdate)).thenReturn(updatedLesson);

        Lessons result = lessonService.updateLesson(lessonToUpdate);

        assertEquals(updatedLesson, result);
        verify(lessonRepositoryMock, times(1)).update(lessonToUpdate);
    }

    @Test
    public void testDeleteLesson() {
        Long lessonId = 1L;

        lessonService.deleteLesson(lessonId);

        verify(lessonRepositoryMock, times(1)).delete(lessonId);
    }
}
