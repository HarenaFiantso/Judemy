package judemy.fiantso.service.QuizService;

import judemy.fiantso.models.Quizzes;
import judemy.fiantso.repository.JudemyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImplementation implements QuizService {
    private final JudemyRepository<Quizzes> quizRepository;

    public QuizServiceImplementation(JudemyRepository<Quizzes> quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public Quizzes createQuiz(Quizzes quiz) {
        return quizRepository.create(quiz);
    }

    @Override
    public List<Quizzes> getAllQuizzes() {
        return quizRepository.getAll();
    }

    @Override
    public Quizzes getQuizById(Long quizId) {
        return quizRepository.getById(quizId);
    }

    @Override
    public Quizzes updateQuiz(Quizzes quiz) {
        return quizRepository.update(quiz);
    }

    @Override
    public void deleteQuiz(Long quiz) {
        this.quizRepository.delete(quiz);
    }
}
