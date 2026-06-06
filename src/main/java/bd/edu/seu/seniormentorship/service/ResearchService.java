package bd.edu.seu.seniormentorship.service;

import bd.edu.seu.seniormentorship.model.ResearchTeam;
import bd.edu.seu.seniormentorship.model.Student;
import bd.edu.seu.seniormentorship.repository.ResearchTeamRepo;
import bd.edu.seu.seniormentorship.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResearchService {

    private final ResearchTeamRepo researchTeamRepo;
    private final StudentService studentService;
    private final StudentRepository studentRepository;

    public ResearchService(ResearchTeamRepo researchTeamRepo, StudentService studentService, StudentRepository studentRepository) {
        this.researchTeamRepo = researchTeamRepo;
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    public List<ResearchTeam> saveList(List<ResearchTeam> list) {
        return researchTeamRepo.saveAll(list);
    }

    public ResearchTeam getByResearchTeamId(String sId) {
        return researchTeamRepo.findBysId(sId).orElse(null);
    }

    public List<ResearchTeam> findAll() {
        List<ResearchTeam> all = researchTeamRepo.findAll();
        if (all == null) return List.of();

        // Filter duplicates based on sId to ensure only unique students are shown
        return all.stream()
            .collect(Collectors.toMap(
                ResearchTeam::getsId,
                rt -> rt,
                (existing, replacement) -> existing))
            .values()
            .stream()
            .collect(Collectors.toList());
    }

    public void deleteById(String sId) {
        researchTeamRepo.findBysId(sId)
                .ifPresent(team -> researchTeamRepo.deleteById(team.getId()));
    }

    /**
     * Syncs students from the student collection to the researchteam collection.
     * Any student with inResearchTeam = true will be added to researchteam collection.
     */
    public void syncStudentsStatus() {
        List<Student> allStudents = studentRepository.findAll();

        List<ResearchTeam> studentsToAddToTeam = allStudents.stream()
            .filter(Student::isInResearchTeam)
            .map(s -> {
                ResearchTeam rt = new ResearchTeam();
                rt.setsId(s.getsId());
                rt.setName(s.getName());
                rt.setProgram(s.getProgram());
                rt.setCgpa(s.getCgpa());
                return rt;
            })
            .collect(Collectors.toList());

        if (!studentsToAddToTeam.isEmpty()) {
            researchTeamRepo.saveAll(studentsToAddToTeam);
        }
    }

    /**
     * Forces ALL students to be part of the research team for testing/visibility.
     */
    public void forceAllStudentsToResearch() {
        List<Student> allStudents = studentRepository.findAll();

        for (Student s : allStudents) {
            s.setInResearchTeam(true);
            studentService.save(s); // Update student status

            ResearchTeam rt = new ResearchTeam();
            rt.setsId(s.getsId());
            rt.setName(s.getName());
            rt.setProgram(s.getProgram());
            rt.setCgpa(s.getCgpa());
            researchTeamRepo.save(rt); // Add to research team collection
        }
    }
}
