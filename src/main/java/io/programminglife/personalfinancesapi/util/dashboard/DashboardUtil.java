package io.programminglife.personalfinancesapi.util.dashboard;

import io.programminglife.personalfinancesapi.entity.Expense;
import io.programminglife.personalfinancesapi.entity.dashboard.TotalAmountForCategory;
import io.programminglife.personalfinancesapi.entity.dashboard.TotalAmountForCategoryGroupByMonth;
import io.programminglife.personalfinancesapi.entity.dashboard.Transaction;

import java.util.List;
import java.util.Optional;

public class DashboardUtil {

    public static Transaction expenseTransaction(Expense expense) {
        Transaction transaction = new Transaction();

        transaction.setTransactionId(expense.getId());
        transaction.setDate(expense.getExpenseDate());
        transaction.setDescription(expense.getLabel());
        transaction.setAmount(expense.getAmount());
        transaction.setCategory(expense.getCategory().getLabel());
        transaction.setPaymentSystem(expense.getPaymentSystem().getLabel());

        return transaction;
    }

}
