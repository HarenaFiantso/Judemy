package judemy.fiantso.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Videos {
    private Long videoId;
    private int lessonId;
    private String title;
    private String url;
    private Integer duration = 0;

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
