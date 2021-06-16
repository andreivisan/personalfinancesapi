package io.programminglife.personalfinancesapi.entity.csv;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CsvEntity {

    private LocalDate transactionDate;

    private String description;

    private Float amount;

    private String category;

    private String paymentSystem;

}
