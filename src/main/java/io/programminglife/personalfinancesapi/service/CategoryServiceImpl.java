package io.programminglife.personalfinancesapi.service;

import io.programminglife.personalfinancesapi.entity.Category;
import io.programminglife.personalfinancesapi.entity.dashboard.TotalAmountForCategory;
import io.programminglife.personalfinancesapi.exception.MyFinancesException;
import io.programminglife.personalfinancesapi.repository.CategoryRepository;
import io.programminglife.personalfinancesapi.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryById(Long categoryId) throws MyFinancesException {
        return categoryRepository.findById(categoryId).orElseThrow(
                () -> new MyFinancesException(String.format("Category with id %s was not found!", categoryId)));
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public Optional<Category> findCategoryByLabel(String label) {
        return categoryRepository.findCategoryByLabel(label);
    }

    @Override
    public List<TotalAmountForCategory> findTotalMonthlyAmountPerCategory(Integer year, Integer month, Long clientId) throws MyFinancesException {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        Optional<List<TotalAmountForCategory>> totalMonthlyAmountPerCategory = categoryRepository.findTotalAmountForCategoryPerUser(startDate, endDate, clientId);
        if (totalMonthlyAmountPerCategory.isPresent()) {
            return totalMonthlyAmountPerCategory.get();
        } else {
            throw new MyFinancesException("Something went wrong with the query!");
        }
    }

}
