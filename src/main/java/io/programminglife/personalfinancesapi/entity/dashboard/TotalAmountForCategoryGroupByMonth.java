package io.programminglife.personalfinancesapi.entity.dashboard;

import java.time.LocalDate;

public interface TotalAmountForCategoryGroupByMonth {

    String getCategory();
    Float getTotal();
    String getExpenseDate();
}
