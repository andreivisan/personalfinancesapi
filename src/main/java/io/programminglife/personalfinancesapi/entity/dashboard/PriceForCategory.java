package io.programminglife.personalfinancesapi.entity.dashboard;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PriceForCategory {

    private String category;

    private Long totalAmount;

    public PriceForCategory(String category, Long totalAmount) {
        this.category = category;
        this.totalAmount = totalAmount;
    }

}
