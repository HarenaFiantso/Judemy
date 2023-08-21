package judemy.fiantso.judemy.RepositoryTests;

import judemy.fiantso.models.Enrollments;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import judemy.fiantso.repository.EnrollmentRepository.EnrollmentRepositoryImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EnrollmentRepositoryImplementationTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private EnrollmentRepositoryImplementation enrollmentRepository;

    @Test
    public void testCreateEnrollment() throws Exception {
        Enrollments enrollment = new Enrollments();
        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        Enrollments createdEnrollment = enrollmentRepository.create(enrollment);

        assertEquals(enrollment, createdEnrollment);
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testGetEnrollmentById() throws Exception {
        Enrollments enrollment = new Enrollments();
        enrollment.setEnrollmentId(1L);

        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getLong("enrollment_id")).thenReturn(1L);

        Enrollments result = enrollmentRepository.getById(1L);

        assertEquals(enrollment.getEnrollmentId(), result.getEnrollmentId());
        verify(preparedStatement, times(1)).executeQuery();
    }

    @Test
    public void testGetAllEnrollments() throws Exception {
        List<Enrollments> enrollments = new ArrayList<>();
        Enrollments enrollment1 = new Enrollments();
        enrollment1.setEnrollmentId(1L);
        Enrollments enrollment2 = new Enrollments();
        enrollment2.setEnrollmentId(2L);
        enrollments.add(enrollment1);
        enrollments.add(enrollment2);

        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getLong("enrollment_id")).thenReturn(1L).thenReturn(2L);

        List<Enrollments> result = enrollmentRepository.getAll();

        assertEquals(2, result.size());
        assertEquals(enrollment1.getEnrollmentId(), result.get(0).getEnrollmentId());
        assertEquals(enrollment2.getEnrollmentId(), result.get(1).getEnrollmentId());
        verify(preparedStatement, times(1)).executeQuery();
    }

    @Test
    public void testUpdateEnrollment() throws Exception {
        Enrollments enrollment = new Enrollments();
        enrollment.setEnrollmentId(1L);
        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        Enrollments updatedEnrollment = enrollmentRepository.update(enrollment);

        assertEquals(enrollment, updatedEnrollment);
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testDeleteEnrollment() throws Exception {
        Long enrollmentIdToDelete = 1L;

        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        enrollmentRepository.delete(enrollmentIdToDelete);

        verify(preparedStatement, times(1)).executeUpdate();
    }
}
