package HistoryAppGradleSecurity.service.impl;

import HistoryAppGradleSecurity.model.entity.Category;
import HistoryAppGradleSecurity.model.enums.CategoryNameEnum;
import HistoryAppGradleSecurity.repository.CategoryRepository;
import HistoryAppGradleSecurity.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findCategoryByName(CategoryNameEnum categoryNameEnum) {
        return categoryRepository.findByName(categoryNameEnum)
                .orElse(null);
    }

    @Override
    public void seedCategories() {
        if (this.categoryRepository.count() == 0) {
            List<Category> categories = Arrays.stream(CategoryNameEnum.values())
                    .map(Category::new)
//                    .map(type -> new Category())
                    .collect(Collectors.toList());
            this.categoryRepository.saveAllAndFlush(categories);
        }
    }

}
