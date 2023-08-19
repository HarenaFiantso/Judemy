package judemy.fiantso.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Enrollments {
    private Long enrollmentId;
    private Users user;
    private Courses course;
    private LocalDateTime enrollmentDate;
    private String status;
}
