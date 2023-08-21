package judemy.fiantso.service.EnrolllmentService;

import judemy.fiantso.models.Enrollments;

import java.util.List;

public interface EnrollmentService {
    Enrollments createEnrollment(Enrollments enrollment);

    List<Enrollments> getAllEnrollments();

    Enrollments getEnrollmentById(Long enrollmentId);

    Enrollments updateEnrollment(Enrollments enrollment);

    void deleteEnrollment(Long enrollment);
}
