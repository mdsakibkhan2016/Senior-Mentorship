package bd.edu.seu.seniormentorship.service;

import bd.edu.seu.seniormentorship.model.Student;
import bd.edu.seu.seniormentorship.model.User;
import bd.edu.seu.seniormentorship.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {




    private final UserRepository userRepository;

    public UserService (UserRepository userRepository) {
        this.userRepository=userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }


    public User create(User user) {
        return userRepository.save(user);
    }
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }



}
