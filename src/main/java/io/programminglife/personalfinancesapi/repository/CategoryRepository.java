package io.programminglife.personalfinancesapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.programminglife.personalfinancesapi.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
