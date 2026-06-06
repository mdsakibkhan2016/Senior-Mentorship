package bd.edu.seu.seniormentorship.service;

import bd.edu.seu.seniormentorship.model.ResearchTeam;
import bd.edu.seu.seniormentorship.repository.ResearchTeamRepo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Uses Google Gemini AI to group students into balanced teams of 3 by CGPA.
 * Calls Gemini via GeminiApiClient (pure REST, no Spring AI dependency).
 */
@Service
public class ResearchTeamGeminiService {

    private static final Logger log = LoggerFactory.getLogger(ResearchTeamGeminiService.class);

    private final GeminiApiClient geminiApiClient;
    private final ResearchTeamRepo researchTeamRepo;
    private final ObjectMapper objectMapper;

    public ResearchTeamGeminiService(GeminiApiClient geminiApiClient,
                                     ResearchTeamRepo researchTeamRepo) {
        this.geminiApiClient  = geminiApiClient;
        this.researchTeamRepo = researchTeamRepo;
        this.objectMapper     = new ObjectMapper();
    }

    public List<List<ResearchTeam>> generateTeams() throws Exception {
        List<ResearchTeam> rawList = researchTeamRepo.findAll();

        if (rawList == null || rawList.isEmpty()) {
            throw new IllegalStateException("No students in the research team pool.");
        }

        // Filter duplicates based on sId to ensure AI only sees each student once
        List<ResearchTeam> all = rawList.stream()
            .collect(java.util.stream.Collectors.toMap(
                ResearchTeam::getsId,
                rt -> rt,
                (existing, replacement) -> existing))
            .values()
            .stream()
            .collect(java.util.stream.Collectors.toList());

        String prompt = buildPrompt(all);
        String rawResponse = geminiApiClient.chat(prompt);
        log.debug("Gemini response:\n{}", rawResponse);

        String cleanJson = stripMarkdownFences(rawResponse);
        return objectMapper.readValue(cleanJson,
                new TypeReference<List<List<ResearchTeam>>>() {});
    }

    private String buildPrompt(List<ResearchTeam> students) {
        StringBuilder sb = new StringBuilder();
        sb.append("You are a team-grouping assistant.\n")
          .append("Group the following students into teams of 3 ")
          .append("so that each team's total CGPA is as balanced as possible.\n\n")
          .append("STRICT OUTPUT RULES:\n")
          .append("- Return ONLY a valid JSON array of arrays. No markdown, no explanation, no code fences.\n")
          .append("- Each inner array must have exactly 3 student objects.\n")
          .append("- Each object must have exactly: sId (string), name (string), cgpa (number).\n")
          .append("- If students cannot be divided evenly by 3, put remaining in last group.\n\n")
          .append("Students:\n[\n");

        for (int i = 0; i < students.size(); i++) {
            ResearchTeam s = students.get(i);
            sb.append(String.format(
                "  {\"sId\":\"%s\",\"name\":\"%s\",\"cgpa\":%.2f}",
                s.getsId(), s.getName(), s.getCgpa()));
            if (i < students.size() - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("]\n");
        return sb.toString();
    }

    private String stripMarkdownFences(String raw) {
        if (raw == null) return "[]";
        String t = raw.strip();
        if (t.startsWith("```")) {
            int nl = t.indexOf('\n');
            if (nl != -1) t = t.substring(nl + 1);
        }
        if (t.endsWith("```")) {
            t = t.substring(0, t.lastIndexOf("```"));
        }
        return t.strip();
    }
}
