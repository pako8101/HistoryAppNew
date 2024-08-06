package HistoryAppGradleSecurity.model.binding;

import HistoryAppGradleSecurity.model.enums.CategoryNameEnum;
import HistoryAppGradleSecurity.model.enums.PeriodEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class ArticleAddBindingModel {

    private long id;
    @Size(min = 3,max = 30, message = "Article title must be between 3 and 30 characters")
    @NotNull
    private String title;
    @NotNull(message = "You have to write author name!")
    @Size(min = 2, max = 30,message = "Author name length must be between 2 and 30 symbols!")
    private String author;
    @NotNull(message = "{add.article.content.message}")
    @Size(min = 10,message = "{add.article.content.message}")
    private String content;
    @NotNull(message = "You have to choose from which period is article!")
    private PeriodEnum period;
    @NotNull(message = "Date of article creation cannot be empty!")
    @PastOrPresent(message = "Article creation cannot be in future!")
    private LocalDate created;
    @NotNull
    private String imageUrl;

    @NotNull
    private Set<CategoryNameEnum> categories;

    public ArticleAddBindingModel() {
        this.categories = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public ArticleAddBindingModel setId(long id) {
        this.id = id;
        return this;
    }

    public @NotNull LocalDate getCreated() {
        return created;
    }

    public ArticleAddBindingModel setCreated(@NotNull LocalDate created) {
        this.created = created;
        return this;
    }

    public @NotNull String getImageUrl() {
        return imageUrl;
    }

    public ArticleAddBindingModel setImageUrl(@NotNull String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }



    public String getAuthor() {
        return author;
    }

    public ArticleAddBindingModel setAuthor(String author) {
        this.author = author;
        return this;
    }

    public PeriodEnum getPeriod() {

        return period;
    }

    public ArticleAddBindingModel setPeriod(PeriodEnum period) {
        this.period = period;
        return this;
    }


    public String getTitle() {
        return title;
    }

    public ArticleAddBindingModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ArticleAddBindingModel setContent(String content) {
        this.content = content;
        return this;
    }


    public Set<CategoryNameEnum> getCategories() {
        return categories;
    }

    public ArticleAddBindingModel setCategories(Set<CategoryNameEnum> categories) {
        this.categories = categories;
        return this;
    }
}
