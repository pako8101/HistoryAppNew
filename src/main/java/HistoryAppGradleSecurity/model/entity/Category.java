package HistoryAppGradleSecurity.model.entity;

import HistoryAppGradleSecurity.model.enums.CategoryNameEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends  BaseEntity{
    @Enumerated(EnumType.STRING)
    @Column(unique = true,nullable = false)
    private CategoryNameEnum name;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Category() {
    }
    public Category(CategoryNameEnum name){
        this.name = name;
    }



    public CategoryNameEnum getName() {
        return name;
    }

    public Category setName(CategoryNameEnum name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Category setDescription(String description) {
        this.description = description;
        return this;
    }
}
