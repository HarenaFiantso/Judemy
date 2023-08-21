package judemy.fiantso.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Lessons {
    private Long lessonId;
    private int course;
    private String title;
    private String description;
    private Integer displayOrder;
}
