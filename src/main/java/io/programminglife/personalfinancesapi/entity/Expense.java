package io.programminglife.personalfinancesapi.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
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
    private Long id;

    @Column(name = "label", nullable = false, columnDefinition = "TEXT")
    private String label;

    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "expense_category_fk"))
    private Category category;

    @Column(name = "expense_date", nullable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime expenseDate;

    @OneToOne
    @JoinColumn(name = "payment_system_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "expense_payment_system_fk"))
    private PaymentSystem paymentSystem;

    @Column(name = "amount", nullable = false)
    private long amount;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name = "client_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "client_expense_fk"))
    private Client client;

}
