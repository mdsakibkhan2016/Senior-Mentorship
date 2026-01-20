package bd.edu.seu.seniormentorship.service;


import bd.edu.seu.seniormentorship.model.Course;
import bd.edu.seu.seniormentorship.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public Course getByCode(String code) {
        Optional<Course> optional = courseRepository.findByCode(code);
        return optional.orElse(null);
    }
}
