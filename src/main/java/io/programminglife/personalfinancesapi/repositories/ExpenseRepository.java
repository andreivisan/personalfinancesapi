package io.programminglife.personalfinancesapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.programminglife.personalfinancesapi.entities.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
