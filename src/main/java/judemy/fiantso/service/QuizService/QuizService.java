package judemy.fiantso.service.QuizService;

import judemy.fiantso.models.Quizzes;

import java.util.List;

public interface QuizService {
    Quizzes createQuiz(Quizzes quiz);
    List<Quizzes> getAllQuizzes();
    Quizzes getQuizById(Long quizId);
    Quizzes updateQuiz(Quizzes quiz);
    void deleteQuiz(Long quiz);
}
