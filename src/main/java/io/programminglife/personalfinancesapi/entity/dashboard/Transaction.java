package io.programminglife.personalfinancesapi.entity.dashboard;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Transaction {

    private LocalDate date;

    private String description;

    private Float amount;

    private String category;

    private String paymentSystem;

}
