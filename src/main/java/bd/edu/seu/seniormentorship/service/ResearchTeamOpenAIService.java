//package bd.edu.seu.seniormentorship.service;
//
//import bd.edu.seu.seniormentorship.model.ResearchTeam;
//import bd.edu.seu.seniormentorship.repository.ResearchTeamRepo;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.ai.chat.client.ChatClient;
//import org.springframework.ai.chat.model.ChatResponse;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ResearchTeamOpenAIService {
//    private final ChatClient chatClient;
//    private final ResearchTeamRepo researchTeamRepo;
//
//    public ResearchTeamOpenAIService(ChatClient.Builder chatClientBuilder, ResearchTeamRepo researchTeamRepo) {
//        this.chatClient = chatClientBuilder.build();
//        this.researchTeamRepo = researchTeamRepo;
//    }
//
//    // OpenAI থেকে ৩ জন করে team generate করবে
//    public List<List<ResearchTeam>> generateTeams() throws Exception {
//        List<ResearchTeam> allStudents = researchTeamRepo.findAll();
//
//        String prompt = createPrompt(allStudents);
//
//        ChatResponse response = chatClient.prompt().user(prompt).call().chatResponse();
//
//        String json = response.getResult().getOutput().getText();
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        // JSON parse করে ৩ জনের টিমের লিস্ট বানাবে
//        return mapper.readValue(json, new TypeReference<List<List<ResearchTeam>>>(){});
//    }
//
//    private String createPrompt(List<ResearchTeam> students) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Group the following students into teams of 3 so that each team's total CGPA is as close as possible.\n");
//        sb.append("Return the output as a JSON array of arrays, each containing 3 objects with keys: sId, name, cgpa.\n");
//        sb.append("[\n");
//        for (ResearchTeam s : students) {
//            sb.append(String.format("{\"sId\":\"%s\",\"name\":\"%s\",\"cgpa\":%.2f},\n", s.getsId(), s.getName(), s.getCgpa()));
//        }
//        sb.append("]\n");
//        return sb.toString();
//    }
//}
