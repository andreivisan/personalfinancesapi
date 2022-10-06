package io.programminglife.personalfinancesapi.service;

import java.util.List;

import io.programminglife.personalfinancesapi.entity.Expense;
import io.programminglife.personalfinancesapi.entity.csv.CsvEntity;
import io.programminglife.personalfinancesapi.entity.dashboard.TotalAmountForCategoryGroupByMonth;
import io.programminglife.personalfinancesapi.entity.dashboard.Transaction;
import io.programminglife.personalfinancesapi.exception.MyFinancesException;

public interface ExpenseService {

    List<Expense> findAll();

    Expense findExpenseById(Long expenseId) throws MyFinancesException;

    Expense saveExpense(CsvEntity csvEntity, Long clientId);

    void deleteExpense(Long expenseId, Long clientId);

    List<Expense> findExpensesByLabelEquals(String label);

    List<Expense> findExpensesByCategoryEquals(Long categoryId);

    List<Expense> findExpensesByPaymentSystemEquals(Long paymentSystemId);

    List<Transaction> findExpensesByClientEquals(Long clientId);

    List<Transaction> findAllTransactions();

    List<Transaction> findAllByExpenseDateBetween(Integer year, Integer month, Long clientId);

    List<TotalAmountForCategoryGroupByMonth> findTotalAmountPerCategoryGroupByMonth(String categoryLabel, Long clientId);

}
