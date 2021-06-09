package io.programminglife.personalfinancesapi.service;

import java.util.List;

import io.programminglife.personalfinancesapi.entity.Category;

public interface CategoryService {

    List<Category> findAll();

    Category findCategoryById(Long categoryId);

    Category saveCategory(Category category);

    void deleteCategory(Long categoryId);

}
