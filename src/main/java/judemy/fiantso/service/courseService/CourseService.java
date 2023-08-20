package judemy.fiantso.service.courseService;

import judemy.fiantso.models.Courses;

import java.util.List;

public interface CourseService {
    Courses createCourse(Courses course);
    List<Courses> getAllCourses();
    Courses getCourseById(Long courseId);
    Courses updateCourse(Courses course);
    void deleteCourse(Long course);
}
