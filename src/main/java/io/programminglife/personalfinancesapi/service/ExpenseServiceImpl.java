package io.programminglife.personalfinancesapi.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.programminglife.personalfinancesapi.entity.Category;
import io.programminglife.personalfinancesapi.entity.Client;
import io.programminglife.personalfinancesapi.entity.Expense;
import io.programminglife.personalfinancesapi.entity.PaymentSystem;
import io.programminglife.personalfinancesapi.entity.csv.CsvEntity;
import io.programminglife.personalfinancesapi.entity.dashboard.Transaction;
import io.programminglife.personalfinancesapi.exception.MyFinancesException;
import io.programminglife.personalfinancesapi.repository.ExpenseRepository;
import io.programminglife.personalfinancesapi.util.csv.CSVUtil;
import io.programminglife.personalfinancesapi.util.dashboard.DashboardUtil;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PaymentSystemService paymentSystemService;

    @Autowired
    private ClientService clientService;

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
    public Expense saveExpense(CsvEntity csvEntity, Long clientId) {
        Optional<Category> categoryOptional = categoryService.findCategoryByLabel(csvEntity.getCategory());
        Category category = categoryOptional.isPresent() ? categoryOptional.get()
                : categoryService.saveCategory(new Category(csvEntity.getCategory()));

        Optional<PaymentSystem> paymentSystemOptional = paymentSystemService
                .findPaymentSystemByLabel(csvEntity.getPaymentSystem());
        PaymentSystem paymentSystem = paymentSystemOptional.isPresent() ? paymentSystemOptional.get()
                : paymentSystemService.savePaymentSystem(new PaymentSystem(csvEntity.getPaymentSystem()));

        Client client = clientService.findClientById(clientId);
        Expense expense = CSVUtil.csvEntityToExpense(csvEntity, category, paymentSystem, client);
        List<Expense> clientExpenses = client.getExpenses();
        clientExpenses.add(expense);
        client.setExpenses(clientExpenses);
        Client savedClient = clientService.saveClient(client);

        return expenseRepository.save(CSVUtil.csvEntityToExpense(csvEntity, category, paymentSystem, savedClient));
    }

    @Override
    public void deleteExpense(Long expenseId, Long clientId) {
        Client client = clientService.findClientById(clientId);
        Expense expense = expenseRepository.getById(expenseId);
        List<Expense> clientExpenses = client.getExpenses();
        clientExpenses.remove(expense);
        client.setExpenses(clientExpenses);
        clientService.saveClient(client);

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

    @Override
    public List<Transaction> findExpensesByClientEquals(Long clientId) {
        return expenseRepository.findExpensesByClientEquals(clientId).stream().map(expense -> DashboardUtil.expenseTransaction(expense)).collect(Collectors.toList());
    }

    @Override
    public List<Transaction> findAllTransactions() {
        return findAll().stream().map(expense -> DashboardUtil.expenseTransaction(expense)).collect(Collectors.toList());
    }

    @Override
    public List<Transaction> findAllByExpenseDateBetween(Integer year, Integer month, Long clientId) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
        return expenseRepository.findAllByExpenseDateBetweenAndClientIdEquals(startDate, endDate, clientId).stream()
                .map(expense -> DashboardUtil.expenseTransaction(expense)).collect(Collectors.toList());
    }

    @Override
    public Map<String, Float> findTotalExpensesForCategoryGroupByMonth(String categoryLabel, Long clientId) {
        Map<String, Float> result = new HashMap<>();

        Optional<Category> currentCategoryOpt = categoryService.findCategoryByLabel(categoryLabel);
        if (currentCategoryOpt.isPresent()) {
            Category currentCategory = currentCategoryOpt.get();
            List<Transaction> clientExpenses = expenseRepository.findAllByCategoryIdEqualsAndClientIdEquals(currentCategory.getId(), clientId).stream()
                    .map(expense -> DashboardUtil.expenseTransaction(expense)).collect(Collectors.toList());

            for (Transaction tx : clientExpenses) {
                String txMonth = tx.getDate().getMonth().name();
                Float txAmount = tx.getAmount();

                result.put(txMonth, result.getOrDefault(txMonth, 0f) + txAmount);
            }
        }

        return result;
    }


}
