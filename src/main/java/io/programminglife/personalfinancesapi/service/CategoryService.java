package io.programminglife.personalfinancesapi.service;

import java.util.List;
import java.util.Optional;

import io.programminglife.personalfinancesapi.entity.Category;
import io.programminglife.personalfinancesapi.entity.dashboard.PriceForCategory;
import io.programminglife.personalfinancesapi.exception.MyFinancesException;

public interface CategoryService {

    List<Category> findAll();

    Category findCategoryById(Long categoryId) throws MyFinancesException;

    Category saveCategory(Category category);

    void deleteCategory(Long categoryId);

    Optional<Category> findCategoryByLabel(String label);

    List<PriceForCategory> findTotalMonthlyAmountPerCategory(Long clientId);

}
