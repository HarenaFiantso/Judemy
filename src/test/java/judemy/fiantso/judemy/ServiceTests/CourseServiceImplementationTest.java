package judemy.fiantso.judemy.ServiceTests;

import judemy.fiantso.models.Courses;
import judemy.fiantso.repository.JudemyRepository;
import judemy.fiantso.service.courseService.CourseService;
import judemy.fiantso.service.courseService.CourseServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CourseServiceImplementationTest {

    private CourseService courseService;
    private JudemyRepository<Courses> courseRepositoryMock;

    @BeforeEach
    public void setUp() {
        courseRepositoryMock = mock(JudemyRepository.class);
        courseService = new CourseServiceImplementation(courseRepositoryMock);
    }

    @Test
    public void testCreateCourse() {
        Courses courseToCreate = new Courses();
        Courses createdCourse = new Courses();
        when(courseRepositoryMock.create(courseToCreate)).thenReturn(createdCourse);

        Courses result = courseService.createCourse(courseToCreate);

        assertEquals(createdCourse, result);
        verify(courseRepositoryMock, times(1)).create(courseToCreate);
    }

    @Test
    public void testGetAllCourses() {
        List<Courses> courseList = new ArrayList<>();
        when(courseRepositoryMock.getAll()).thenReturn(courseList);

        List<Courses> result = courseService.getAllCourses();

        assertEquals(courseList, result);
        verify(courseRepositoryMock, times(1)).getAll();
    }

    @Test
    public void testGetCourseById() {
        Long courseId = 1L;
        Courses course = new Courses();
        when(courseRepositoryMock.getById(courseId)).thenReturn(course);

        Courses result = courseService.getCourseById(courseId);

        assertEquals(course, result);
        verify(courseRepositoryMock, times(1)).getById(courseId);
    }

    @Test
    public void testUpdateCourse() {
        Courses courseToUpdate = new Courses();
        Courses updatedCourse = new Courses();
        when(courseRepositoryMock.update(courseToUpdate)).thenReturn(updatedCourse);

        Courses result = courseService.updateCourse(courseToUpdate);

        assertEquals(updatedCourse, result);
        verify(courseRepositoryMock, times(1)).update(courseToUpdate);
    }

    @Test
    public void testDeleteCourse() {
        Long courseId = 1L;

        courseService.deleteCourse(courseId);

        verify(courseRepositoryMock, times(1)).delete(courseId);
    }
}
