package bd.edu.seu.seniormentorship.service;

import bd.edu.seu.seniormentorship.model.Student;
import bd.edu.seu.seniormentorship.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void save(Student student) {
        studentRepository.save(student);
    }

    public Student getByStudentId(String sId) {
        return studentRepository.findBysId(sId).orElse(null);
    }

    public List<Student> findAll() {
        return studentRepository.findByInResearchTeamFalse();
    }

    public void deleteById(String sId) {
        studentRepository.findBysId(sId)
                .ifPresent(s -> studentRepository.deleteById(s.getId()));
    }

    public void update(Student student) {
        Optional<Student> existing = studentRepository.findById(student.getId());
        if (existing.isPresent()) {
            Student s = existing.get();
            s.setName(student.getName());
            s.setProgram(student.getProgram());
            s.setCgpa(student.getCgpa());
            studentRepository.save(s);
        }
    }

    public void markAsInResearchTeam(String sId) {
        studentRepository.findBysId(sId).ifPresent(s -> {
            s.setInResearchTeam(true);
            studentRepository.save(s);
        });
    }

    public void markAsNotInResearchTeam(String sId) {
        studentRepository.findBysId(sId).ifPresent(s -> {
            s.setInResearchTeam(false);
            studentRepository.save(s);
        });
    }
}
