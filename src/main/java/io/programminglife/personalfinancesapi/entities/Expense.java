package io.programminglife.personalfinancesapi.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "expense")
@Getter
@Setter
@NoArgsConstructor
public class Expense {

    @Id
    @SequenceGenerator(name = "expense_sequence", sequenceName = "expense_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expense_sequence")
    @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "label", nullable = false, columnDefinition = "TEXT")
    private String label;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "expense_date", nullable = false)
    private LocalDate expenseDate;

    @OneToOne
    @JoinColumn(name = "payment_system_id")
    private PaymentSystem paymentSystem;

    @Column(name = "amount", nullable = false)
    private long amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
