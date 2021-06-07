package io.programminglife.personalfinancesapi.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "label", nullable = false)
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

}
