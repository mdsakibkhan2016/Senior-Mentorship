package bd.edu.seu.seniormentorship.repository;

import bd.edu.seu.seniormentorship.model.ResearchTeam;
import bd.edu.seu.seniormentorship.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResearchTeamRepo extends MongoRepository<ResearchTeam, String> {
    Optional<ResearchTeam> findBysId(String sId);
}
