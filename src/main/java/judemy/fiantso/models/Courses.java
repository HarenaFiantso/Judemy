package judemy.fiantso.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Courses {
    private Long courseId;
    private String title;
    private String description;
    private Integer duration;
}
