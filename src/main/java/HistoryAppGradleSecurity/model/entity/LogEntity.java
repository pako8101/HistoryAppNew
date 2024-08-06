package HistoryAppGradleSecurity.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "logs")
public class LogEntity extends BaseEntity{
    @ManyToOne
    private UserEnt userEnt;
    @ManyToOne
    private Article article;
    @Column(name = "action",nullable = false)
    private String action;
    @Column(name = "date_time",nullable = false)
    private LocalDateTime dateTime;

@Column
    private Instant appearanceTime;

    public LogEntity() {
    }

    public Instant getAppearanceTime() {
        return appearanceTime;
    }

    public LogEntity setAppearanceTime(Instant appearanceTime) {
        this.appearanceTime = appearanceTime;
        return this;
    }

    public UserEnt getUserEnt() {
        return userEnt;
    }

    public LogEntity setUserEnt(UserEnt userEnt) {
        this.userEnt = userEnt;
        return this;
    }

    public Article getArticle() {
        return article;
    }

    public LogEntity setArticle(Article article) {
        this.article = article;
        return this;
    }

    public String getAction() {
        return action;
    }

    public LogEntity setAction(String action) {
        this.action = action;
        return this;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LogEntity setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }
}
