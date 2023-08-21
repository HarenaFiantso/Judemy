package judemy.fiantso.judemy.RepositoryTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import judemy.fiantso.models.Courses;
import judemy.fiantso.repository.CourseRepository.CourseRepositoryImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseRepositoryImplementationTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private CourseRepositoryImplementation courseRepository;

    @Test
    public void testCreateCourse() throws Exception {
        Courses course = new Courses();
        course.setDuration(10);

        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        Courses createdCourse = courseRepository.create(course);

        assertEquals(course, createdCourse);
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testGetCourseById() throws Exception {
        Courses expectedCourse = new Courses();
        expectedCourse.setCourseId(1L);

        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getLong("course_id")).thenReturn(1L);

        Courses result = courseRepository.getById(1L);

        assertEquals(expectedCourse.getCourseId(), result.getCourseId());
        verify(preparedStatement, times(1)).executeQuery();
    }


    @Test
    public void testGetAllCourses() throws Exception {
        List<Courses> courses = new ArrayList<>();

        Courses course1 = new Courses();
        course1.setCourseId(1L);

        Courses course2 = new Courses();
        course2.setCourseId(2L);

        courses.add(course1);
        courses.add(course2);

        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getLong("course_id")).thenReturn(1L).thenReturn(2L);

        List<Courses> result = courseRepository.getAll();

        assertEquals(2, result.size());
        assertEquals(course1.getCourseId(), result.get(0).getCourseId());
        assertEquals(course2.getCourseId(), result.get(1).getCourseId());
        verify(preparedStatement, times(1)).executeQuery();
    }


    @Test
    public void testUpdateCourse() throws Exception {
        Courses course = new Courses();
        course.setCourseId(1L);
        course.setTitle("Izany ary ny test");
        course.setDescription("Test mirefotra tsara");
        course.setDuration(10);

        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        Courses updatedCourse = courseRepository.update(course);

        assertEquals(course, updatedCourse);
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testDeleteCourse() throws Exception {
        Long courseIdToDelete = 1L;

        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        courseRepository.delete(courseIdToDelete);

        verify(preparedStatement, times(1)).executeUpdate();
    }

}
