package io.programminglife.personalfinancesapi.repository;

import io.programminglife.personalfinancesapi.entity.Category;
import io.programminglife.personalfinancesapi.entity.dashboard.TotalAmountForCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT * FROM category WHERE label = :label", nativeQuery = true)
    Optional<Category> findCategoryByLabel(@Param("label") String label);

    @Query(value = "SELECT c.label as category, SUM(e.amount) as total " +
            "FROM category c JOIN expense e ON c.id = e.category_id " +
            "WHERE e.client_id = :userId AND e.expense_date BETWEEN :startDate AND :endDate " +
            "GROUP BY category ORDER BY total DESC ", nativeQuery = true)
    Optional<List<TotalAmountForCategory>> findTotalAmountForCategoryPerUser(LocalDate startDate, LocalDate endDate, Long userId);

}
