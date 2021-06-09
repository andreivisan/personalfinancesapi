package io.programminglife.personalfinancesapi.service;

import java.util.List;

import io.programminglife.personalfinancesapi.entity.Category;
import io.programminglife.personalfinancesapi.exception.MyFinancesException;

public interface CategoryService {

    List<Category> findAll();

    Category findCategoryById(Long categoryId) throws MyFinancesException;

    Category saveCategory(Category category);

    void deleteCategory(Long categoryId);

}
