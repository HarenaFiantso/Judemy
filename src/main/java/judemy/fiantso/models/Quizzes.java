package judemy.fiantso.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Quizzes {
    private Long quizId;
    private int lessonId;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private Integer correctAnswer;
}
