package bd.edu.seu.seniormentorship;

import bd.edu.seu.seniormentorship.model.Student;
import bd.edu.seu.seniormentorship.service.StudentService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StudentServiceTest {
    @Autowired
    private StudentService studentService;

    @Test
    public void save() {
        Student student = new Student();
        student.setsId("103");
        student.setName("Mr. Tabul");
        student.setProgram("BBA");

        studentService.save(student);
    }
}
