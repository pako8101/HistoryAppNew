package HistoryAppGradleSecurity.model.entity;

import jakarta.persistence.*;
import org.springframework.security.core.userdetails.User;

@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity{
    @Column(nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT",nullable = false)
    private String url;
    @ManyToOne(optional = false)
    private UserEnt author;
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private Article article;

    public Picture() {
    }

    public String getTitle() {
        return title;
    }

    public Picture setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Picture setUrl(String url) {
        this.url = url;
        return this;
    }

    public UserEnt getAuthor() {
        return author;
    }

    public Picture setAuthor(UserEnt author) {
        this.author = author;
        return this;
    }

    public Article getArticle() {
        return article;
    }

    public Picture setArticle(Article article) {
        this.article = article;
        return this;
    }
}
