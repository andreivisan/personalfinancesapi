package io.programminglife.personalfinancesapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.programminglife.personalfinancesapi.entities.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query(value = "SELECT * FROM expense WHERE label = :label", nativeQuery = true)
    List<Expense> findExpensesByLabelEquals(@Param("label") String label);

    @Query(value = "SELECT * FROM expense WHERE category_id = :categoryId", nativeQuery = true)
    List<Expense> findExpensesByCategoryEquals(@Param("categoryId") Long categoryId);

    @Query(value = "SELECT * FROM expense WHERE payment_system_id = :paymentSystemId", nativeQuery = true)
    List<Expense> findExpensesByPaymentSystemEquals(@Param("paymentSystemId") Long paymentSystemId);

}
