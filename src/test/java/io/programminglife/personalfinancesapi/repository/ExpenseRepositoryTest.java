package io.programminglife.personalfinancesapi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import io.programminglife.personalfinancesapi.entity.Category;
import io.programminglife.personalfinancesapi.entity.Client;
import io.programminglife.personalfinancesapi.entity.Expense;
import io.programminglife.personalfinancesapi.entity.PaymentSystem;

@DataJpaTest
public class ExpenseRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Test
    public void test_no_expense_if_repository_is_empty() {
        List<Expense> expenses = expenseRepository.findAll();

        assertEquals(0, expenses.size());
    }

    @Test
    public void test_save_expense() {
        Expense testExpense = expenseRepository.save(createExpense());

        assertNotNull(testExpense);
        assertEquals(100L, testExpense.getAmount());
    }

    @Test
    public void test_get_expense_by_id() {
        Expense testExpense = expenseRepository.save(createExpense());

        Expense expense = expenseRepository.getById(testExpense.getId());

        assertNotNull(expense);
        assertEquals(testExpense.getAmount(), expense.getAmount());
    }

    @Test
    public void test_get_all_expenses() {
        expenseRepository.save(createExpense());
        expenseRepository.save(createExpense());

        List<Expense> expenses = expenseRepository.findAll();

        assertNotNull(expenses);
        assertEquals(2, expenses.size());
    }

    @Test
    public void test_delete_expense_by_id() {
        Expense expense = expenseRepository.save(createExpense());

        expenseRepository.deleteById(expense.getId());

        List<Expense> expenses = expenseRepository.findAll();

        assertEquals(0, expenses.size());
    }

    @Test
    public void test_get_expenses_by_label() {
        expenseRepository.save(createExpense());

        List<Expense> expenses = expenseRepository.findExpensesByLabelEquals("Albert Heijn");

        assertNotNull(expenses);
        assertEquals(1, expenses.size());
        assertEquals("Albert Heijn", expenses.get(0).getLabel());
    }

    @Test
    public void test_get_expense_by_category() {
        Expense expense = expenseRepository.save(createExpense());

        List<Expense> expenses = expenseRepository.findExpensesByCategoryEquals(expense.getCategory().getId());

        assertNotNull(expenses);
        assertEquals("Groceries", expenses.get(0).getCategory().getLabel());
    }

    @Test
    public void test_get_all_expenses_by_date_between() {
        expenseRepository.save(createExpenseByDate(LocalDate.of(2021, 04, 21)));
        expenseRepository.save(createExpenseByDate(LocalDate.of(2021, 04, 25)));
        expenseRepository.save(createExpenseByDate(LocalDate.of(2021, 04, 29)));

        expenseRepository.save(createExpenseByDate(LocalDate.of(2021, 05, 26)));
        expenseRepository.save(createExpenseByDate(LocalDate.of(2021, 05, 22)));
        expenseRepository.save(createExpenseByDate(LocalDate.of(2021, 05, 25)));

        expenseRepository.save(createExpenseByDate(LocalDate.of(2021, 06, 01)));
        expenseRepository.save(createExpenseByDate(LocalDate.of(2021, 06, 02)));
        expenseRepository.save(createExpenseByDate(LocalDate.of(2021, 06, 03)));

        List<Expense> expenses = expenseRepository.findAllByExpenseDateBetween(LocalDate.of(2021, 04, 01),
                LocalDate.of(2021, 05, 01));

        assertNotNull(expenses);
        assertEquals(3, expenses.size());
    }

    private Expense createExpense() {
        return createExpenseByDate(LocalDate.now());
    }

    private Expense createExpenseByDate(LocalDate expenseDate) {
        Category category = entityManager.persist(new Category("Groceries"));
        PaymentSystem paymentSystem = entityManager.persist(new PaymentSystem("iDeal"));

        Client client = new Client("andrei.m.visan@gmail.com", "1234");
        client.setName("name");
        client.setUsername("username");
        Client savedClient = entityManager.persist(client);

        Expense expense = new Expense();
        expense.setExpenseDate(expenseDate);
        expense.setClient(savedClient);
        expense.setAmount(100f);
        expense.setCategory(category);
        expense.setLabel("Albert Heijn");
        expense.setPaymentSystem(paymentSystem);

        return expense;
    }

}
