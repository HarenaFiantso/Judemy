package judemy.fiantso.models;

import lombok.Data;

@Data
public class Lessons {
    private Long lessonId;
    private Courses course;
    private String title;
    private String description;
    private Integer displayOrder;
}
