package judemy.fiantso.controller;

import judemy.fiantso.models.Lessons;
import judemy.fiantso.service.LessonService.LessonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lessons")
public class LessonController {
    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping("/create")
    public ResponseEntity<Lessons> createLesson(@RequestBody Lessons lesson) {
        Lessons createLesson = lessonService.createLesson(lesson);
        if (createLesson != null) {
            return ResponseEntity.ok(createLesson);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Lessons>> getAllLessons() {
        List<Lessons> lessons = lessonService.getAllLessons();
        return ResponseEntity.ok(lessons);
    }

    @GetMapping("/{lessonId}")
    public ResponseEntity<Lessons> getLessonById(@PathVariable Long lessonId) {
        Lessons lesson = lessonService.getLessonById(lessonId);
        if (lesson != null) {
            return ResponseEntity.ok(lesson);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{lessonId}")
    public Lessons updateLesson(@PathVariable Long lessonId, @RequestBody Lessons lesson) {
        lesson.setLessonId(lessonId);
        return this.lessonService.updateLesson(lesson);
    }

    @DeleteMapping("/{lessonId}")
    public void deleteLesson(@PathVariable Long lessonId) {
        this.lessonService.deleteLesson(lessonId);
    }
}
