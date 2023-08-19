package judemy.fiantso.models;

import lombok.Data;

@Data
public class Quizzes {
    private Long quizId;
    private Lessons lesson;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private Integer correctAnswer;
}
