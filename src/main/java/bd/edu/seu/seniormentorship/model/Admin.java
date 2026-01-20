package bd.edu.seu.seniormentorship.model;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Admin {
    @Id
    private String id;
    private String name;
    private String email;
    private String scode;
    private String password;


    private List<String> roles;

    public Admin() {
    }

    public Admin(String password, String scode, String email, String name, String id) {
        this.password = password;
        this.scode = scode;
        this.email = email;
        this.name = name;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getScode() {
        return scode;
    }

    public void setScode(String scode) {
        this.scode = scode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
