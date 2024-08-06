package HistoryAppGradleSecurity.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="users")
public class UserEnt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,name = "full_name")
    private String fullName;
    @Column(name = "age")
    private int age;
    @Column(nullable = false,unique = true)
    private String email;

    @Column(unique = true,nullable = false)
    private String username;


    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserRoleEnt> roles ;

    public UserEnt() {

    }

    public UserEnt setRoles(List<UserRoleEnt> roles) {
        this.roles = roles;
        return this;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public UserEnt setUsername(String username) {
        this.username = username;
        return this;
    }
    public UserEnt addRole(UserRoleEnt role) {
        this.roles.add(role);
        return this;
    }
    public String getEmail() {
        return email;
    }

    public UserEnt setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserEnt setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }



    public String getPassword() {
        return password;
    }

    public UserEnt setPassword(String password) {
        this.password = password;
        return this;
    }

    public List<UserRoleEnt> getRoles() {
        return roles;
    }

    public Long getId() {
        return id;
    }

    public UserEnt setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", password='" + (password != null ? "[PROVIDED]" : "[N/A]") + '\'' +
                ", roles=" + roles +
                '}';
    }
}
