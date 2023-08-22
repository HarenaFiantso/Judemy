package judemy.fiantso.judemy.ServiceTests;

import judemy.fiantso.models.Enrollments;
import judemy.fiantso.repository.JudemyRepository;
import judemy.fiantso.service.EnrolllmentService.EnrollmentService;
import judemy.fiantso.service.EnrolllmentService.EnrollmentServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class EnrollmentServiceImplementationTest {

    @Mock
    private JudemyRepository<Enrollments> enrollmentRepositoryMock;

    private EnrollmentService enrollmentService;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        enrollmentService = new EnrollmentServiceImplementation(enrollmentRepositoryMock);
    }

    @Test
    public void testCreateEnrollment() {
        Enrollments enrollment = new Enrollments();
        when(enrollmentRepositoryMock.create(enrollment)).thenReturn(enrollment);

        Enrollments result = enrollmentService.createEnrollment(enrollment);

        assertEquals(enrollment, result);
        verify(enrollmentRepositoryMock, times(1)).create(enrollment);
    }

    @Test
    public void testGetAllEnrollments() {
        List<Enrollments> enrollments = new ArrayList<>();
        when(enrollmentRepositoryMock.getAll()).thenReturn(enrollments);

        List<Enrollments> result = enrollmentService.getAllEnrollments();

        assertEquals(enrollments, result);
        verify(enrollmentRepositoryMock, times(1)).getAll();
    }

    @Test
    public void testGetEnrollmentById() {
        Long enrollmentId = 1L;
        Enrollments enrollment = new Enrollments();
        when(enrollmentRepositoryMock.getById(enrollmentId)).thenReturn(enrollment);

        Enrollments result = enrollmentService.getEnrollmentById(enrollmentId);

        assertEquals(enrollment, result);
        verify(enrollmentRepositoryMock, times(1)).getById(enrollmentId);
    }

    @Test
    public void testUpdateEnrollment() {
        Enrollments enrollmentToUpdate = new Enrollments();
        Enrollments updatedEnrollment = new Enrollments();
        when(enrollmentRepositoryMock.update(enrollmentToUpdate)).thenReturn(updatedEnrollment);

        Enrollments result = enrollmentService.updateEnrollment(enrollmentToUpdate);

        assertEquals(updatedEnrollment, result);
        verify(enrollmentRepositoryMock, times(1)).update(enrollmentToUpdate);
    }

    @Test
    public void testDeleteEnrollment() {
        Long enrollmentId = 1L;

        enrollmentService.deleteEnrollment(enrollmentId);

        verify(enrollmentRepositoryMock, times(1)).delete(enrollmentId);
    }
}
