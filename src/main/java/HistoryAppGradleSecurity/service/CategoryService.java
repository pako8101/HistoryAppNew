package HistoryAppGradleSecurity.service;

import HistoryAppGradleSecurity.model.entity.Category;
import HistoryAppGradleSecurity.model.enums.CategoryNameEnum;

public interface CategoryService {
    Category findCategoryByName(CategoryNameEnum categoryNameEnum);

    void seedCategories();

}
