package bd.edu.seu.seniormentorship.repository;


import bd.edu.seu.seniormentorship.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
    Optional<Student> findBysId(String sId);
   // Student findBySId(String sId);
    List<Student> findByInResearchTeamFalse();


}