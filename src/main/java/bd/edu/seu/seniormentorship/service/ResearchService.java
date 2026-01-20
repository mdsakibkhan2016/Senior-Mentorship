package bd.edu.seu.seniormentorship.service;

import bd.edu.seu.seniormentorship.model.ResearchTeam;
import bd.edu.seu.seniormentorship.repository.ResearchTeamRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ResearchService {
    private final ResearchTeamRepo researchTeamRepo;

    public ResearchService(ResearchTeamRepo researchTeamRepo) {
        this.researchTeamRepo = researchTeamRepo;
    }
  
    public List<ResearchTeam> saveList(List<ResearchTeam> ResearchTeam) {
      return   researchTeamRepo.saveAll(ResearchTeam);
    }

    public ResearchTeam getByResearchTeamId(String sId) {
        Optional<ResearchTeam> sOptional = researchTeamRepo.findBysId(sId);
        return sOptional.orElse(null);
    }
    public List<ResearchTeam> findAll() {
        return researchTeamRepo.findAll();
    }
//    public void deleteById(String sId) {
//        researchTeamRepo.findBysId(sId).ifPresent(researchTeamRepo::delete);
//    }
    public void deleteById(String sId) {
        Optional<ResearchTeam> optionalTeam = researchTeamRepo.findBysId(sId);
        optionalTeam.ifPresent(team -> researchTeamRepo.deleteById(team.getId())); // Mongo diye id delete
    }


}
