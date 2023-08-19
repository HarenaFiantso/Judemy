package judemy.fiantso.models;

import lombok.Data;

@Data
public class Videos {
    private Long videoId;
    private Lessons lesson;
    private String title;
    private String url;
    private Integer duration;
}
