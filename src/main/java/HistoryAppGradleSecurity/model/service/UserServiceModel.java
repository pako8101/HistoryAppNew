package HistoryAppGradleSecurity.model.service;

import HistoryAppGradleSecurity.model.entity.Action;
import HistoryAppGradleSecurity.model.enums.PeriodEnum;

import java.util.Set;

public class UserServiceModel {
    private Long id;
    private String username;
    private String fullName;

    private String password;

    private String email;

    private int age;

    private Set<Action> actions;

    private PeriodEnum periods;

    public UserServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public UserServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserServiceModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public int getAge() {
        return age;
    }

    public UserServiceModel setAge(int age) {
        this.age = age;
        return this;
    }

    public Set<Action> getActions() {
        return actions;
    }

    public UserServiceModel setActions(Set<Action> actions) {
        this.actions = actions;
        return this;
    }

    public PeriodEnum getPeriods() {
        return periods;
    }

    public UserServiceModel setPeriods(PeriodEnum periods) {
        this.periods = periods;
        return this;
    }

    @Override
    public String toString() {
        return "UserServiceModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", password='" + (password != null ? "[PROVIDED]" : "[N/A]") + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", actions=" + actions +
                ", periods=" + periods +
                '}';
    }
}
