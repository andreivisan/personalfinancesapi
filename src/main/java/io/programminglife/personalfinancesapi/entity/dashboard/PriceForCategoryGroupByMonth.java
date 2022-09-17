package io.programminglife.personalfinancesapi.entity.dashboard;

public class PriceForCategoryGroupByMonth {

    private String category;
    private Float amount;
    private String month;

    public PriceForCategoryGroupByMonth(String category, Float amount, String month) {
        this.category = category;
        this.amount = amount;
        this.month = month;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
