package io.programminglife.personalfinancesapi.service;

import java.util.List;

import io.programminglife.personalfinancesapi.entity.Expense;
import io.programminglife.personalfinancesapi.entity.csv.CsvEntity;
import io.programminglife.personalfinancesapi.exception.MyFinancesException;

public interface ExpenseService {

    List<Expense> findAll();

    Expense findExpenseById(Long expenseId) throws MyFinancesException;

    Expense saveExpense(CsvEntity csvEntity);

    void deleteExpense(Long expenseId);

    List<Expense> findExpensesByLabelEquals(String label);

    List<Expense> findExpensesByCategoryEquals(Long categoryId);

    List<Expense> findExpensesByPaymentSystemEquals(Long paymentSystemId);

}
