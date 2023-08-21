package judemy.fiantso.controller;

import judemy.fiantso.models.Quizzes;
import judemy.fiantso.service.QuizService.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quizzes")
public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/create")
    public ResponseEntity<Quizzes> createQuiz(@RequestBody Quizzes quiz) {
        Quizzes createQuiz = quizService.createQuiz(quiz);
        if (createQuiz != null) {
            return ResponseEntity.ok(createQuiz);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Quizzes>> getAllQuizzes() {
        List<Quizzes> quizzes = quizService.getAllQuizzes();
        return ResponseEntity.ok(quizzes);
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<Quizzes> getQuizById(@PathVariable Long quizId) {
        Quizzes quiz = quizService.getQuizById(quizId);
        if (quiz != null) {
            return ResponseEntity.ok(quiz);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{quizId}")
    public Quizzes updateQuiz(@PathVariable Long quizId, @RequestBody Quizzes quiz) {
        quiz.setQuizId(quizId);
        return this.quizService.updateQuiz(quiz);
    }

    @DeleteMapping("/{quizId}")
    public void deleteQuiz(@PathVariable Long quizId) {
        this.quizService.deleteQuiz(quizId);
    }
}
