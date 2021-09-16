package io.programminglife.personalfinancesapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.programminglife.personalfinancesapi.entity.Expense;
import io.programminglife.personalfinancesapi.entity.csv.CsvEntity;
import io.programminglife.personalfinancesapi.entity.dashboard.Transaction;
import io.programminglife.personalfinancesapi.exception.MyFinancesException;
import io.programminglife.personalfinancesapi.security.CurrentUser;
import io.programminglife.personalfinancesapi.security.UserPrincipal;
import io.programminglife.personalfinancesapi.service.ExpenseService;

@RestController
@RequestMapping("api/v1/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/save")
    public ResponseEntity<Expense> save(@RequestBody CsvEntity csvEntity, @CurrentUser UserPrincipal currentUser) {
        return ResponseEntity.ok().body(expenseService.saveExpense(csvEntity, currentUser.getId()));
    }

    @GetMapping
    public ResponseEntity<List<Expense>> findAll() {
        return ResponseEntity.ok().body(expenseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> findExpenseById(@PathVariable(value = "id") Long id) {
        try {
            return ResponseEntity.ok().body(expenseService.findExpenseById(id));
        } catch (MyFinancesException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") Long id, @CurrentUser UserPrincipal currentUser) {
        expenseService.deleteExpense(id, currentUser.getId());
    }

    @GetMapping("/label/{label}")
    public List<Expense> findExpensesByLabelEquals(@PathVariable(value = "label") String label) {
        return expenseService.findExpensesByLabelEquals(label);
    }

    @GetMapping("/category/{categoryId}")
    public List<Expense> findExpensesByCategoryEquals(@PathVariable(value = "categoryId") Long categoryId) {
        return expenseService.findExpensesByCategoryEquals(categoryId);
    }

    @GetMapping("/paymentsystem/{paymentSystemId}")
    public List<Expense> findExpensesByPaymentSystemEquals(
            @PathVariable(value = "paymentSystemId") Long paymentSystemId) {
        return expenseService.findExpensesByPaymentSystemEquals(paymentSystemId);
    }

    @GetMapping("/toTransactions")
    public ResponseEntity<List<Transaction>> findAllTransactions() {
        return ResponseEntity.ok().body(expenseService.findAllTransactions());
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> findAllTransactionsForCurrentUser(@CurrentUser UserPrincipal currentUser) {
        return ResponseEntity.ok().body(expenseService.findExpensesByClientEquals(currentUser.getId()));
    }

    @GetMapping("/transactions/{year}/{month}")
    public ResponseEntity<List<Transaction>> findAllTransactionsForCurrentUserByMonth(
            @CurrentUser UserPrincipal currentUser, @PathVariable(value = "year") Integer year,
            @PathVariable(value = "month") Integer month) {
        return ResponseEntity.ok().body(expenseService.findAllByExpenseDateBetween(year, month, currentUser.getId()));
    }

}
