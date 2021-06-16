package io.programminglife.personalfinancesapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.programminglife.personalfinancesapi.entity.Category;
import io.programminglife.personalfinancesapi.entity.Expense;
import io.programminglife.personalfinancesapi.entity.PaymentSystem;
import io.programminglife.personalfinancesapi.entity.csv.CsvEntity;
import io.programminglife.personalfinancesapi.exception.MyFinancesException;
import io.programminglife.personalfinancesapi.repository.ExpenseRepository;
import io.programminglife.personalfinancesapi.util.csv.CSVUtil;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PaymentSystemService paymentSystemService;

    @Override
    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    @Override
    public Expense findExpenseById(Long expenseId) throws MyFinancesException {
        return expenseRepository.findById(expenseId).orElseThrow(
                () -> new MyFinancesException(String.format("Expense with id %s could not be found!", expenseId)));
    }

    @Override
    public Expense saveExpense(CsvEntity csvEntity) {
        Optional<Category> categoryOptional = categoryService.findCategoryByLabel(csvEntity.getCategory());
        Category category = categoryOptional.isPresent() ? categoryOptional.get()
                : categoryService.saveCategory(new Category(csvEntity.getCategory()));

        Optional<PaymentSystem> paymentSystemOptional = paymentSystemService
                .findPaymentSystemByLabel(csvEntity.getPaymentSystem());
        PaymentSystem paymentSystem = paymentSystemOptional.isPresent() ? paymentSystemOptional.get()
                : paymentSystemService.savePaymentSystem(new PaymentSystem(csvEntity.getPaymentSystem()));

        return expenseRepository.save(CSVUtil.csvEntityToExpense(csvEntity, category, paymentSystem));
    }

    @Override
    public void deleteExpense(Long expenseId) {
        expenseRepository.deleteById(expenseId);
    }

    @Override
    public List<Expense> findExpensesByLabelEquals(String label) {
        return expenseRepository.findExpensesByLabelEquals(label);
    }

    @Override
    public List<Expense> findExpensesByCategoryEquals(Long categoryId) {
        return expenseRepository.findExpensesByCategoryEquals(categoryId);
    }

    @Override
    public List<Expense> findExpensesByPaymentSystemEquals(Long paymentSystemId) {
        return expenseRepository.findExpensesByPaymentSystemEquals(paymentSystemId);
    }

}
