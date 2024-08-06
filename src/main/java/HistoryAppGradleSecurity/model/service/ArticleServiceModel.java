package HistoryAppGradleSecurity.model.service;

import HistoryAppGradleSecurity.model.enums.CategoryNameEnum;
import HistoryAppGradleSecurity.model.enums.PeriodEnum;
import HistoryAppGradleSecurity.model.view.PictureViewModel;

import java.time.LocalDate;
import java.util.Set;

public class ArticleServiceModel {
    private long id;


    private String title;
    private String author;
    private String content;
    private String imageUrl;
    private LocalDate created;
    private Set<PictureViewModel> pictures;
    private PeriodEnum period;
    private Set<CategoryNameEnum>categories;

    public ArticleServiceModel() {
    }

    public PeriodEnum getPeriod() {
        return period;
    }

    public ArticleServiceModel setPeriod(PeriodEnum period) {
        this.period = period;
        return this;
    }

    public LocalDate getCreated() {
        return created;
    }

    public ArticleServiceModel setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

    public Set<PictureViewModel> getPictures() {
        return pictures;
    }

    public ArticleServiceModel setPictures(Set<PictureViewModel> pictures) {
        this.pictures = pictures;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ArticleServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ArticleServiceModel setContent(String content) {
        this.content = content;
        return this;
    }



    public Set<CategoryNameEnum> getCategories() {
        return categories;
    }

    public ArticleServiceModel setCategories(Set<CategoryNameEnum> categories) {
        this.categories = categories;
        return this;
    }

    public long getId() {
        return id;
    }

    public ArticleServiceModel setId(long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ArticleServiceModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public ArticleServiceModel setAuthor(String author) {
        this.author = author;
        return this;
    }
}
