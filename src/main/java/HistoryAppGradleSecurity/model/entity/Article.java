package HistoryAppGradleSecurity.model.entity;

import HistoryAppGradleSecurity.model.enums.PeriodEnum;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    @Column(name = "title",nullable = false,unique = true)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    @Column(nullable = false)
    private String author;
    @Enumerated(EnumType.STRING)
    private PeriodEnum period;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate created;
//@NotNull
//@Column
//    private Instant established = Instant.now();
    @Column(name = "image_url",nullable = false)
    private String imageUrl;
    @OneToMany(mappedBy = "article",
            targetEntity = Picture.class,
            fetch = FetchType.EAGER)
    private Set<Picture>pictures;
    @ManyToMany
    private Set<Category>categories;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false,unique = true)
    private UserEnt user;
//,foreignKey = @ForeignKey(name = "FKlc3sm3utetrj1sx4v9ahwopnr")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LogEntity> logs;

    public Article() {
        this.pictures = new HashSet<>();
        this.categories = new HashSet<>();
    }

    public UserEnt getUser() {
        return user;
    }

    public Article setUser(UserEnt user) {
        this.user = user;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Article setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

//    public Instant getEstablished() {
//        return established;
//    }
//
//    public Article setEstablished( Instant established) {
//        this.established = established;
//        return this;
//    }

    public List<LogEntity> getLogs() {
        return logs;
    }

    public Article setLogs(List<LogEntity> logs) {
        this.logs = logs;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Article setTitle(String title) {
        this.title = title;
        return this;
    }

    public long getId() {
        return id;
    }

    public Article setId(long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Article setContent(String content) {
        this.content = content;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Article setAuthor(String author) {
        this.author = author;
        return this;
    }

    public PeriodEnum getPeriod() {
        return period;
    }

    public Article setPeriod(PeriodEnum period) {
        this.period = period;
        return this;
    }

    public LocalDate getCreated() {
        return created;
    }

    public Article setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public Article setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
        return this;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
