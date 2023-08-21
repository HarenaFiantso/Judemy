package judemy.fiantso.service.EnrolllmentService;

import judemy.fiantso.models.Enrollments;
import judemy.fiantso.repository.JudemyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentServiceImplementation implements EnrollmentService {
    private final JudemyRepository<Enrollments> enrollmentRepository;

    public EnrollmentServiceImplementation(JudemyRepository<Enrollments> enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public Enrollments createEnrollment(Enrollments enrollment) {
        return enrollmentRepository.create(enrollment);
    }

    @Override
    public List<Enrollments> getAllEnrollments() {
        return enrollmentRepository.getAll();
    }

    @Override
    public Enrollments getEnrollmentById(Long enrollmentId) {
        return enrollmentRepository.getById(enrollmentId);
    }

    @Override
    public Enrollments updateEnrollment(Enrollments enrollment) {
        return enrollmentRepository.update(enrollment);
    }

    @Override
    public void deleteEnrollment(Long enrollment) {
        enrollmentRepository.delete(enrollment);
    }
}
