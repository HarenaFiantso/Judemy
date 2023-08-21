package judemy.fiantso.service.LessonService;

import judemy.fiantso.models.Lessons;

import java.util.List;

public interface LessonRepository {
    Lessons createLesson(Lessons lesson);
    List<Lessons> getAllLessons();
    Lessons getLessonById(Long lessonId);
    Lessons updateLesson(Lessons lesson);
    void deleteLesson(Long lesson);
}
