package io.programminglife.personalfinancesapi.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "client", uniqueConstraints = { @UniqueConstraint(name = "client_email_unique", columnNames = "email"),
        @UniqueConstraint(name = "client_iban_unique", columnNames = "iban") })
@Getter
@Setter
@NoArgsConstructor
public class Client {

    @Id
    @SequenceGenerator(name = "client_sequence", sequenceName = "client_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_sequence")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, columnDefinition = "TEXT")
    private String email;

    @Column(name = "iban", nullable = false, columnDefinition = "TEXT")
    private String iban;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Expense> expenses;

    public void addExpense(Expense expense) {
        if (!this.expenses.contains(expense)) {
            this.expenses.add(expense);
            expense.setClient(this);
        }
    }

    public void removeExpense(Expense expense) {
        if (this.expenses.contains(expense)) {
            this.expenses.remove(expense);
            expense.setClient(null);
        }
    }
}
