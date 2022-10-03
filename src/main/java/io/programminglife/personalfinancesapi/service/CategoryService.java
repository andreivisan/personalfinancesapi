package io.programminglife.personalfinancesapi.service;

import io.programminglife.personalfinancesapi.entity.Category;
import io.programminglife.personalfinancesapi.entity.dashboard.TotalAmountForCategory;
import io.programminglife.personalfinancesapi.exception.MyFinancesException;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findAll();

    Category findCategoryById(Long categoryId) throws MyFinancesException;

    Category saveCategory(Category category);

    void deleteCategory(Long categoryId);

    Optional<Category> findCategoryByLabel(String label);

    List<TotalAmountForCategory> findTotalMonthlyAmountPerCategory(Integer year, Integer month, Long clientId) throws MyFinancesException;

}
