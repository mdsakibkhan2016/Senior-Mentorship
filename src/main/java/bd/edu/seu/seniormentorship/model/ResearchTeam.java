package bd.edu.seu.seniormentorship.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "researchteam")
public class ResearchTeam extends Student {

    private String researchTopic;

    public ResearchTeam() {}

    public ResearchTeam(Student student) {
        this.setsId(student.getsId());
        this.setName(student.getName());
        this.setProgram(student.getProgram());
        this.setCgpa(student.getCgpa());
    }

    public ResearchTeam(String sId, String name, String program, double cgpa) {
        super(sId, name, program, cgpa);
    }

    public ResearchTeam(String sId, String name, String program, double cgpa, String researchTopic) {
        super(sId, name, program, cgpa);
        this.researchTopic = researchTopic;
    }

    public String getResearchTopic() { return researchTopic; }
    public void setResearchTopic(String researchTopic) { this.researchTopic = researchTopic; }
}
