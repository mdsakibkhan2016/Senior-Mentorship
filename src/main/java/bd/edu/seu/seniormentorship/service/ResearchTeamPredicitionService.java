package bd.edu.seu.seniormentorship.service;

import bd.edu.seu.seniormentorship.model.ResearchTeam;
import bd.edu.seu.seniormentorship.service.ResearchService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ResearchTeamPredicitionService {
    private final ChatClient chatClient;
    private final ResearchService researchService;

    public ResearchTeamPredicitionService(ChatClient.Builder chatClientBuilder,
                                          ResearchService researchService) {
        this.chatClient = chatClientBuilder.build();
        this.researchService = researchService;
    }

    public void predict(){
        String question = "What is your name?";
        ChatResponse chatResponse = chatClient.prompt().user(question).call().chatResponse();
        System.out.println(chatResponse.getResult().getOutput().getText());
    }
    public void predict2(){
       List<ResearchTeam> researchTeamList= researchService.findAll();
       StringBuilder prompt = new StringBuilder();
       prompt.append("Based onthe data set . predict next cgpa in this format (withour bracket)");
       prompt.append("\nFormat: (3.288 cgpa)");
       prompt.append("\n)");
       for (ResearchTeam researchTeam : researchTeamList) {
           prompt.append(researchTeam.getProgram()+ ","+researchTeam.getCgpa()+"\n");
       }

        System.out.println(prompt);
       ChatResponse chatResponse= chatClient.prompt().user(prompt.toString()).call().chatResponse();
        System.out.println(chatResponse.getResult().getOutput().getText());

    }

    public String predictBalancedTeams() {
        List<ResearchTeam> researchTeamList = researchService.findAll();

        StringBuilder prompt = new StringBuilder();
        prompt.append("Below is a list of students with their names, programs, and CGPAs.\n");
        prompt.append("Using this data, create groups of 3 students in such a way that the total CGPA of each group is as close as possible.\n");
        prompt.append("Give each group a unique name (e.g., Team Alpha, Team Beta).\n");
        prompt.append("List each student's name, program, and CGPA. At the end of each group, show the total and average CGPA.\n");
        prompt.append("Provide the output in English and follow the format below:\n\n");

        prompt.append("Group 1: Team Alpha\n");
        prompt.append("Md Sakib Khan (CSE) - CGPA: 2.33\n");
        prompt.append("Rakin Israt Irtiza (CSE) - CGPA: 3.55\n");
        prompt.append("Abid (CSE) - CGPA: 3.99\n");
        prompt.append("Total CGPA: 9.87\n");
        prompt.append("Average CGPA: 3.29\n\n");

        prompt.append("Now create teams using the following students:\n\n");

        for (ResearchTeam student : researchTeamList) {
            prompt.append(student.getName())
                    .append(" (")
                    .append(student.getProgram())
                    .append(") - CGPA: ")
                    .append(student.getCgpa())
                    .append("\n");
        }

        System.out.println("========== Prompt ==========");
        System.out.println(prompt);

        ChatResponse chatResponse = chatClient.prompt()
                .user(prompt.toString())
                .call()
                .chatResponse();

        System.out.println("========== AI Response ==========");
        System.out.println(chatResponse.getResult().getOutput().getText());
        return chatResponse.getResult().getOutput().getText();
    }




}







































//package bd.edu.seu.seniormentorship.service;
//
//
//import bd.edu.seu.seniormentorship.model.ResearchTeam;
//import org.springframework.ai.chat.client.ChatClient;
//import org.springframework.ai.chat.model.ChatResponse;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ResearchTeamPredicitionService {
//    private final ChatClient chatClient;
////    private final ResearchTeamPredicitionService researchTeamPredicitionService;
////    private final ResearchService researchService;
//
//
//    public ResearchTeamPredicitionService(ChatClient.Builder chatClientBuilder, ResearchTeamPredicitionService researchTeamPredicitionService, ResearchService researchService) {
//        this.chatClient = chatClientBuilder.build();
////        this.researchTeamPredicitionService = researchTeamPredicitionService;
////        this.researchService = researchService;
//    }
//
//    public void predict(){
//        String question = "What is your name?";
//        ChatResponse chatResponse = chatClient.prompt().user(question).call().chatResponse();
//        System.out.println(chatResponse.getResult().getOutput().getText());
//    }
//
////    public void predictResearchTeam() {
////        List<ResearchTeam> researchTeamList = researchService.findAll();
////        StringBuilder prompt = new StringBuilder();
////        prompt.append("Based on the cgpa yo make research teams per team have 3 member (without brackets) ");
////        prompt.append("\nFormat :(3.78 cgpa)");
////        prompt.append("\n");
////        for (ResearchTeam researchTeam : researchTeamList) {
////            prompt.append(researchTeam.getCgpa() + "," + researchTeam.getName() + "\n");
////        }
////
////        System.out.println(prompt);
////        ChatResponse chatResponse = chatClient.prompt().user(prompt.toString()).call().chatResponse();
////        System.out.println(chatResponse.getResult().getOutput().getText());
////    }
//
//    }
//
//
//
