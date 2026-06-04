package bd.edu.seu.seniormentorship;

import bd.edu.seu.seniormentorship.model.Course;
import bd.edu.seu.seniormentorship.service.CourseService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CourseServiceTest {
    @Autowired
    private CourseService courseService;

    @Test
    public void save() {
        Course course = new Course("CSE103", "Advanced Java", 4);
        courseService.save(course);
    }
}
