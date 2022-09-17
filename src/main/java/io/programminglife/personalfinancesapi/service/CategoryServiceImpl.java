package io.programminglife.personalfinancesapi.service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import io.programminglife.personalfinancesapi.entity.dashboard.PriceForCategory;
import io.programminglife.personalfinancesapi.entity.dashboard.PriceForCategoryGroupByMonth;
import io.programminglife.personalfinancesapi.entity.dashboard.Transaction;
import io.programminglife.personalfinancesapi.util.dashboard.DashboardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.programminglife.personalfinancesapi.entity.Category;
import io.programminglife.personalfinancesapi.entity.Expense;
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
    public List<PriceForCategory> findTotalMonthlyAmountPerCategory(Integer year, Integer month, Long clientId) {
        List<PriceForCategory> totalMonthlyAmountPerCategory = new ArrayList<>();

        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        List<Transaction> clientExpenses = expenseRepository.findAllByExpenseDateBetweenAndClientIdEquals(startDate, endDate, clientId).stream()
                .map(expense -> {
                    return DashboardUtil.expenseTransaction(expense);
                }).collect(Collectors.toList());

        for (Transaction transaction : clientExpenses) {
            String category = transaction.getCategory();
            Float expenseAmount = transaction.getAmount();

            Optional<PriceForCategory> recordOpt = DashboardUtil.containsTransactionForCategory(totalMonthlyAmountPerCategory, category);

            if (!totalMonthlyAmountPerCategory.isEmpty() && recordOpt.isPresent()) {
                PriceForCategory record = recordOpt.get();
                totalMonthlyAmountPerCategory.remove(record);
                record.setTotalAmount(record.getTotalAmount() + expenseAmount);
                totalMonthlyAmountPerCategory.add(record);
            } else {
                totalMonthlyAmountPerCategory.add(new PriceForCategory(category, expenseAmount));
            }

        }

        Collections.sort(totalMonthlyAmountPerCategory);
        Collections.reverse(totalMonthlyAmountPerCategory);

        return totalMonthlyAmountPerCategory;
    }

}
