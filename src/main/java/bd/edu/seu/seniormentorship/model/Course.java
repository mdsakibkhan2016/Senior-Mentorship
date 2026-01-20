package bd.edu.seu.seniormentorship.model;

import org.springframework.data.annotation.Id;

public class Course {
    @Id
    private String id;
    private String code;
    private String title;
    private double credits;

    public Course(String code, String title, double credits) {
        this.code = code;
        this.title = title;
        this.credits = credits;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }
}
