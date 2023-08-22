package judemy.fiantso.judemy.ControllerTests;

import judemy.fiantso.models.Enrollments;
import judemy.fiantso.service.EnrolllmentService.EnrollmentService;
import judemy.fiantso.controller.EnrollmentController;
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

public class EnrollmentControllerTest {

    @Mock
    private EnrollmentService enrollmentServiceMock;

    private MockMvc mockMvc;
    private EnrollmentController enrollmentController;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        enrollmentController = new EnrollmentController(enrollmentServiceMock);
        mockMvc = MockMvcBuilders.standaloneSetup(enrollmentController).build();
    }

    @Test
    public void testCreateEnrollment() throws Exception {
        Enrollments enrollment = new Enrollments();
        when(enrollmentServiceMock.createEnrollment(any())).thenReturn(enrollment);

        mockMvc.perform(post("/enrollments/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.enrollmentId").value(enrollment.getEnrollmentId()));
    }

    @Test
    public void testGetAllEnrollments() throws Exception {
        List<Enrollments> enrollments = new ArrayList<>();
        when(enrollmentServiceMock.getAllEnrollments()).thenReturn(enrollments);

        mockMvc.perform(get("/enrollments/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(enrollments.size()));
    }

    @Test
    public void testGetEnrollmentById() throws Exception {
        Long enrollmentId = 1L;
        Enrollments enrollment = new Enrollments();
        enrollment.setEnrollmentId(enrollmentId);

        when(enrollmentServiceMock.getEnrollmentById(enrollmentId)).thenReturn(enrollment);

        mockMvc.perform(get("/enrollments/{enrollmentId}", enrollmentId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.enrollmentId").value(enrollmentId));
    }

    @Test
    public void testDeleteEnrollment() throws Exception {
        Long enrollmentId = 1L;

        mockMvc.perform(delete("/enrollments/{enrollmentId}", enrollmentId))
                .andExpect(status().isOk());

        verify(enrollmentServiceMock, times(1)).deleteEnrollment(enrollmentId);
    }
}
