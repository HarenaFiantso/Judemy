package judemy.fiantso.judemy.ControllerTests;

import judemy.fiantso.controller.CourseController;
import judemy.fiantso.models.Courses;
import judemy.fiantso.service.courseService.CourseService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CourseControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CourseService courseServiceMock;

    @Before
    public void setUp() {
        courseServiceMock = mock(CourseService.class);
        CourseController courseController = new CourseController(courseServiceMock);
        mockMvc = MockMvcBuilders.standaloneSetup(courseController).build();
    }

    @Test
    public void testCreateCourse() throws Exception {
        Courses courseToCreate = new Courses();
        Courses createdCourse = new Courses();
        when(courseServiceMock.createCourse(courseToCreate)).thenReturn(createdCourse);

        mockMvc.perform(post("/courses/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.courseId").value(createdCourse.getCourseId()));
    }

    @Test
    public void testGetAllCourses() throws Exception {
        List<Courses> courseList = new ArrayList<>();
        when(courseServiceMock.getAllCourses()).thenReturn(courseList);

        mockMvc.perform(get("/courses/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetCourseById() throws Exception {
        Long courseId = 1L;
        Courses course = new Courses();
        course.setCourseId(courseId);

        // Configurer le mock pour renvoyer l'objet course lors de l'appel à getCourseById
        when(courseServiceMock.getCourseById(courseId)).thenReturn(course);

        mockMvc.perform(get("/courses/{courseId}", courseId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.courseId").value(courseId));
    }

    @Test
    public void testDeleteCourse() throws Exception {
        Long courseId = 1L;

        mockMvc.perform(delete("/courses/{courseId}", courseId))
                .andExpect(status().isOk());

        verify(courseServiceMock, times(1)).deleteCourse(courseId);
    }
}
