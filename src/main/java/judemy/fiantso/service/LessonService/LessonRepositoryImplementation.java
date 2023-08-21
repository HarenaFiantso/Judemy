package judemy.fiantso.service.LessonService;

import judemy.fiantso.models.Lessons;
import judemy.fiantso.repository.JudemyRepository;

import java.util.List;

public class LessonRepositoryImplementation implements LessonRepository {
    private final JudemyRepository<Lessons> lessonRepository;

    public LessonRepositoryImplementation(JudemyRepository<Lessons> lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public Lessons createLesson(Lessons lesson) {
        return null;
    }

    @Override
    public List<Lessons> getAllLessons() {
        return null;
    }

    @Override
    public Lessons getLessonById(Long lessonId) {
        return null;
    }

    @Override
    public Lessons updateLesson(Lessons lesson) {
        return null;
    }

    @Override
    public void deleteLesson(Long lesson) {

    }
}
