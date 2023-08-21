package judemy.fiantso.controller;

import judemy.fiantso.models.Enrollments;
import judemy.fiantso.service.EnrolllmentService.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/create")
    public ResponseEntity<Enrollments> createEnrollment(@RequestBody Enrollments enrollment) {
        Enrollments createdEnrollment = enrollmentService.createEnrollment(enrollment);
        if (createdEnrollment != null) {
            return ResponseEntity.ok(createdEnrollment);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Enrollments>> getAllEnrollment() {
        List<Enrollments> enrollments = enrollmentService.getAllEnrollments();
        return ResponseEntity.ok(enrollments);
    }

    @GetMapping("/{enrollmentId}")
    public ResponseEntity<Enrollments> getEnrollmentById(@PathVariable Long enrollmentId) {
        Enrollments enrollment = enrollmentService.getEnrollmentById(enrollmentId);
        if (enrollment != null) {
            return ResponseEntity.ok(enrollment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{enrollmentId}")
    public Enrollments updateEnrollment(@PathVariable Long enrollmentId, @RequestBody Enrollments enrollment) {
        enrollment.setEnrollmentId(enrollmentId);
        return this.enrollmentService.updateEnrollment(enrollment);
    }

    @DeleteMapping("/{enrollmentId}")
    public void deleteUser(@PathVariable Long enrollmentId) {
        this.enrollmentService.deleteEnrollment(enrollmentId);
    }
}
