package io.programminglife.personalfinancesapi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payment_system")
@Getter
@Setter
@NoArgsConstructor
public class PaymentSystem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "label", nullable = false)
    private String label;

    public PaymentSystem(String label) {
        this.label = label;
    }

}
