package judemy.fiantso.service.LessonService;

import judemy.fiantso.models.Lessons;
import judemy.fiantso.repository.JudemyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonServiceImplementation implements LessonService {
    private final JudemyRepository<Lessons> lessonRepository;

    public LessonServiceImplementation(JudemyRepository<Lessons> lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public Lessons createLesson(Lessons lesson) {
        return lessonRepository.create(lesson);
    }

    @Override
    public List<Lessons> getAllLessons() {
        return lessonRepository.getAll();
    }

    @Override
    public Lessons getLessonById(Long lessonId) {
        return lessonRepository.getById(lessonId);
    }

    @Override
    public Lessons updateLesson(Lessons lesson) {
        return lessonRepository.update(lesson);
    }

    @Override
    public void deleteLesson(Long lesson) {
        this.lessonRepository.delete(lesson);
    }
}
