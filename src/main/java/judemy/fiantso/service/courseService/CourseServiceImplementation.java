package judemy.fiantso.service.courseService;

import judemy.fiantso.models.Courses;
import judemy.fiantso.repository.JudemyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImplementation implements CourseService {
    private final JudemyRepository<Courses> courseRepository;

    public CourseServiceImplementation(JudemyRepository<Courses> courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Courses createCourse(Courses course) {
        return courseRepository.create(course);
    }

    @Override
    public List<Courses> getAllCourses() {
        return courseRepository.getAll();
    }

    @Override
    public Courses getCourseById(Long courseId) {
        return courseRepository.getById(courseId);
    }

    @Override
    public Courses updateCourse(Courses course) {
        return courseRepository.update(course);
    }

    @Override
    public void deleteCourse(Long course) {
        courseRepository.delete(course);
    }
}
