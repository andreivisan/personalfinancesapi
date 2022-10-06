package io.programminglife.personalfinancesapi.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import io.programminglife.personalfinancesapi.entity.dashboard.TotalAmountForCategoryGroupByMonth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.programminglife.personalfinancesapi.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query(value = "SELECT * FROM expense WHERE label = :label", nativeQuery = true)
    List<Expense> findExpensesByLabelEquals(@Param("label") String label);

    @Query(value = "SELECT * FROM expense WHERE category_id = :categoryId", nativeQuery = true)
    List<Expense> findExpensesByCategoryEquals(@Param("categoryId") Long categoryId);

    @Query(value = "SELECT * FROM expense WHERE payment_system_id = :paymentSystemId", nativeQuery = true)
    List<Expense> findExpensesByPaymentSystemEquals(@Param("paymentSystemId") Long paymentSystemId);

    @Query(value = "SELECT * FROM expense WHERE client_id = :clientId", nativeQuery = true)
    List<Expense> findExpensesByClientEquals(@Param("clientId") Long clientId);

    @Query(value = "SELECT SUM(amount) FROM expense WHERE category_id = :categoryId", nativeQuery = true)
    Long findTotalAmountByCategory(@Param("categoryId") Long categoryId);

    List<Expense> findAllByExpenseDateBetweenAndClientIdEquals(LocalDate startDate, LocalDate endDate, Long clientId);
    @Query(value = "SELECT c.label as category, SUM(e.amount) as total, TO_CHAR(e.expense_date, 'Month') as expenseDate " +
            "FROM category c JOIN expense e ON c.id = e.category_id " +
            "WHERE e.client_id = :userId AND c.id = :categoryId " +
            "GROUP BY category, expenseDate", nativeQuery = true)
    Optional<List<TotalAmountForCategoryGroupByMonth>> findTotalAmountPerCategoryGroupByMonth(Long categoryId, Long userId);

}
