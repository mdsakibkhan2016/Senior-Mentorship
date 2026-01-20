package bd.edu.seu.seniormentorship.model;


import org.springframework.data.annotation.Id;

public class Student {
    @Id
    private String id;
    private String sId;
    private String name;
    private String program;
    private double cgpa;

    private boolean inResearchTeam = false;

    public boolean isInResearchTeam() {
        return inResearchTeam;
    }

    public void setInResearchTeam(boolean inResearchTeam) {
        this.inResearchTeam = inResearchTeam;
    }



    public Student(String sId, String name, String program, double cgpa) {
        this.sId = sId;
        this.name = name;
        this.program = program;
        this.cgpa = cgpa;
    }

    public Student() {

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }
}