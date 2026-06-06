package bd.edu.seu.seniormentorship.service;

import bd.edu.seu.seniormentorship.model.ResearchTeam;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Uses Gemini AI to generate a human-readable team grouping (plain text).
 */
@Service
public class ResearchTeamPredicitionService {

    private final GeminiApiClient geminiApiClient;
    private final ResearchService researchService;

    public ResearchTeamPredicitionService(GeminiApiClient geminiApiClient,
                                          ResearchService researchService) {
        this.geminiApiClient = geminiApiClient;
        this.researchService = researchService;
    }

    public String predictBalancedTeams() throws Exception {
        List<ResearchTeam> list = researchService.findAll();

        StringBuilder prompt = new StringBuilder();
        prompt.append("Below is a list of students with their names, programs, and CGPAs.\n")
              .append("Create groups of 3 students so that each group's total CGPA is balanced.\n")
              .append("Give each group a unique name (e.g. Team Alpha). ")
              .append("Show each student's name, program, and CGPA. ")
              .append("At the end of each group, show total and average CGPA.\n\n")
              .append("Students:\n");

        for (ResearchTeam s : list) {
            prompt.append(s.getName())
                  .append(" (").append(s.getProgram()).append(")")
                  .append(" - CGPA: ").append(s.getCgpa()).append("\n");
        }

        return geminiApiClient.chat(prompt.toString());
    }
}
