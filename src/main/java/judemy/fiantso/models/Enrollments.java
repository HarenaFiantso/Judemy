package judemy.fiantso.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Enrollments {
    private Long enrollmentId;
    private int userId;
    private int courseId;
    private Date enrollmentDate;
    private String status;

}
