package io.programminglife.personalfinancesapi.entity.dashboard;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PriceForCategory implements Comparable<PriceForCategory> {

    private String category;

    private Float totalAmount;

    public PriceForCategory(String category, Float totalAmount) {
        this.category = category;
        this.totalAmount = totalAmount;
    }

    public String getCategory() {
        return category;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    @Override
    public int compareTo(PriceForCategory record) {
        if (getTotalAmount() == null || record.getTotalAmount() == null) {
            return 0;
        }
        return getTotalAmount().compareTo(record.getTotalAmount());
    }
}
