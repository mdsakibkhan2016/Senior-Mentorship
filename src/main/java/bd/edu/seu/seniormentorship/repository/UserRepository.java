package bd.edu.seu.seniormentorship.repository;


import bd.edu.seu.seniormentorship.model.Student;
import bd.edu.seu.seniormentorship.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    List<Student> findByNameIgnoreCase(String name);
        List<Student> findAllById(String Id);
        Optional<Student> findByEmail(String name);
        Optional<Student> findByEmailAndPassword(String email, String password);
//    List<Student> findAllByOnnoKisuIgnoreCase(String o);

   // Optional<User>findByEmail(String email)


    }

