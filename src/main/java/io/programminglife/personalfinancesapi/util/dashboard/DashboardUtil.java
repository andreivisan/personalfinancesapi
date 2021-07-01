package io.programminglife.personalfinancesapi.util.dashboard;

import io.programminglife.personalfinancesapi.entity.Expense;
import io.programminglife.personalfinancesapi.entity.dashboard.Transaction;

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
