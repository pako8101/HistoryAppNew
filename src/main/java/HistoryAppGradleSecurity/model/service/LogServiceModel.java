package HistoryAppGradleSecurity.model.service;

import java.time.Instant;
import java.time.LocalDateTime;

public class LogServiceModel {

    private Long id;

    private String user;
    private String article;
    private String action;
    private LocalDateTime dateTime;
    private Instant appearanceTime;
    public LogServiceModel() {
    }

    public Instant getAppearanceTime() {
        return appearanceTime;
    }

    public LogServiceModel setAppearanceTime(Instant appearanceTime) {
        this.appearanceTime = appearanceTime;
        return this;
    }

    public Long getId() {
        return id;
    }

    public LogServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUser() {
        return user;
    }

    public LogServiceModel setUser(String user) {
        this.user = user;
        return this;
    }

    public String getArticle() {
        return article;
    }

    public LogServiceModel setArticle(String article) {
        this.article = article;
        return this;
    }

    public String getAction() {
        return action;
    }

    public LogServiceModel setAction(String action) {
        this.action = action;
        return this;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LogServiceModel setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }
}
