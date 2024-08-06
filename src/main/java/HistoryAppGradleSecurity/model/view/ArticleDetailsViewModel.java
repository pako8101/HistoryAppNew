package HistoryAppGradleSecurity.model.view;

import HistoryAppGradleSecurity.model.entity.UserEnt;
import HistoryAppGradleSecurity.model.enums.CategoryNameEnum;
import HistoryAppGradleSecurity.model.enums.PeriodEnum;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@SuperBuilder
public class ArticleDetailsViewModel {
    private long id;
    private PeriodEnum period;
private CategoryNameEnum category;
    private String title;
    private UserEnt author;
    private String created;
    private String content;

    private Set<PictureViewModel> pictures;

    public ArticleDetailsViewModel() {
    }

    public CategoryNameEnum getCategory() {
        return category;
    }

    public ArticleDetailsViewModel setCategory(CategoryNameEnum category) {
        this.category = category;
        return this;
    }

    public long getId() {
        return id;
    }

    public ArticleDetailsViewModel setId(long id) {
        this.id = id;
        return this;
    }

    public String getCreated() {
        return created;
    }

    public ArticleDetailsViewModel setCreated(String created) {
        this.created = created;
        return this;
    }

    public UserEnt getAuthor() {
        return author;
    }

    public ArticleDetailsViewModel setAuthor(UserEnt author) {
        this.author = author;
        return this;
    }

    public PeriodEnum getPeriod() {
        return period;
    }

    public ArticleDetailsViewModel setPeriod(PeriodEnum period) {
        this.period = period;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ArticleDetailsViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ArticleDetailsViewModel setContent(String content) {
        this.content = content;
        return this;
    }

    public Set<PictureViewModel> getPictures() {
        return pictures;
    }

    public ArticleDetailsViewModel setPictures(Set<PictureViewModel> pictures) {
        this.pictures = pictures;
        return this;
    }
}
