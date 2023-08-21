package judemy.fiantso.judemy.RepositoryTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import judemy.fiantso.models.Lessons;
import judemy.fiantso.repository.LessonRepository.LessonRepositoryImplementation;

@ExtendWith(MockitoExtension.class)
public class LessonRepositoryImplementationTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private LessonRepositoryImplementation lessonRepository;

    @BeforeEach
    public void setUp() throws Exception {
        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
    }

    @Test
    public void testCreateLesson() throws Exception {
        Lessons lesson = new Lessons();
        lesson.setCourseId(1);
        lesson.setTitle("Fiantso Harena");
        lesson.setDescription("Harena Fiantso");
        lesson.setDisplayOrder(1);

        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        Lessons createdLesson = lessonRepository.create(lesson);

        assertEquals(lesson.getCourseId(), createdLesson.getCourseId());
        assertEquals(lesson.getTitle(), createdLesson.getTitle());
        assertEquals(lesson.getDescription(), createdLesson.getDescription());
        assertEquals(lesson.getDisplayOrder(), createdLesson.getDisplayOrder());
        verify(preparedStatement, times(1)).executeUpdate();
    }


    @Test
    public void testGetLessonById() throws Exception {
        Lessons expectedLesson = new Lessons();
        expectedLesson.setLessonId(1L);

        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getLong("lesson_id")).thenReturn(expectedLesson.getLessonId());

        Lessons result = lessonRepository.getById(1L);

        assertEquals(expectedLesson.getLessonId(), result.getLessonId());
        verify(preparedStatement, times(1)).executeQuery();
    }


    @Test
    public void testGetAllLessons() throws Exception {
        List<Lessons> lessons = new ArrayList<>();
        Lessons lesson1 = new Lessons();
        lesson1.setLessonId(1L);
        Lessons lesson2 = new Lessons();
        lesson2.setLessonId(2L);
        lessons.add(lesson1);
        lessons.add(lesson2);

        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getLong("lesson_id")).thenReturn(1L, 2L);

        List<Lessons> result = lessonRepository.getAll();

        assertEquals(2, result.size());
        assertEquals(lesson1.getLessonId(), result.get(0).getLessonId());
        assertEquals(lesson2.getLessonId(), result.get(1).getLessonId());
        verify(preparedStatement, times(1)).executeQuery();
    }


    @Test
    public void testUpdateLesson() throws Exception {
        Lessons lesson = new Lessons();
        lesson.setLessonId(1L);
        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        Lessons updatedLesson = lessonRepository.update(lesson);
        assertEquals(lesson, updatedLesson);

        verify(preparedStatement, times(1)).executeUpdate();

        verify(preparedStatement).setInt(1, lesson.getCourseId());
        verify(preparedStatement).setString(2, lesson.getTitle());
        verify(preparedStatement).setString(3, lesson.getDescription());
        verify(preparedStatement).setInt(4, lesson.getDisplayOrder());
        verify(preparedStatement).setLong(5, lesson.getLessonId());
    }


    @Test
    public void testDeleteLesson() throws Exception {
        when(preparedStatement.executeUpdate()).thenReturn(1);

        lessonRepository.delete(1L);

        verify(preparedStatement, times(1)).executeUpdate();
    }
}
