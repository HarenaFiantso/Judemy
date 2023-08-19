package judemy.fiantso.models;

import lombok.Data;

@Data
public class Courses {
    private Long courseId;
    private String title;
    private String description;
    private Integer duration;
}
