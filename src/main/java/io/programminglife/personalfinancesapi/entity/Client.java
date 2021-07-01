package io.programminglife.personalfinancesapi.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "client")
@Getter
@Setter
@NoArgsConstructor
public class Client {

    @Id
    @SequenceGenerator(name = "client_sequence", sequenceName = "client_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_sequence")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(name = "username", nullable = false, unique = true, columnDefinition = "TEXT")
    private String username;

    @Column(name = "email", nullable = false, unique = true, columnDefinition = "TEXT")
    @JsonIgnore
    private String email;

    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Expense> expenses;

    public Client(String email, String password) {
        this.email = email;
        this.password = password;
    }

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
