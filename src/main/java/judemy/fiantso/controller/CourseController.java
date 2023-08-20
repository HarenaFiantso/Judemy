package judemy.fiantso.controller;

import judemy.fiantso.models.Courses;
import judemy.fiantso.service.courseService.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/create")
    public ResponseEntity<Courses> createCourse(@RequestBody Courses course) {
        Courses createdCourse = courseService.createCourse(course);
        if (createdCourse != null) {
            return ResponseEntity.ok(createdCourse);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Courses>> getAllCourses() {
        List<Courses> users = courseService.getAllCourses();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Courses> getCourseById(@PathVariable Long courseId) {
        Courses course = courseService.getCourseById(courseId);
        if (course != null) {
            return ResponseEntity.ok(course);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{courseId}")
    public Courses updateCourse(@PathVariable Long courseId, @RequestBody Courses course) {
        course.setCourseId(courseId);
        return this.courseService.updateCourse(course);
    }

    @DeleteMapping("/{courseId}")
    public void deleteCourse(@PathVariable Long courseId) {
        this.courseService.deleteCourse(courseId);
    }
}
