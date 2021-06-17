package io.programminglife.personalfinancesapi.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.programminglife.personalfinancesapi.entity.Category;
import io.programminglife.personalfinancesapi.entity.dashboard.PriceForCategory;
import io.programminglife.personalfinancesapi.exception.MyFinancesException;
import io.programminglife.personalfinancesapi.repository.CategoryRepository;
import io.programminglife.personalfinancesapi.repository.ExpenseRepository;

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
    public List<PriceForCategory> findTotalMonthlyAmountPerCategory() {
        List<PriceForCategory> totalMonthlyAmountPerCateogry = new ArrayList<>();
        List<Category> categories = findAll();

        for (Category category : categories) {
            Long totalAmountForCategory = expenseRepository.findTotalAmountByCategory(category.getId());
            totalMonthlyAmountPerCateogry.add(new PriceForCategory(category.getLabel(), totalAmountForCategory));
        }

        return totalMonthlyAmountPerCateogry.stream()
                .sorted(Comparator.comparingLong(PriceForCategory::getTotalAmount).reversed())
                .collect(Collectors.toList());
    }

}
