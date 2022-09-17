package io.programminglife.personalfinancesapi.controller;

import java.util.List;
import java.util.Map;

import io.programminglife.personalfinancesapi.entity.dashboard.PriceForCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.programminglife.personalfinancesapi.entity.Category;
import io.programminglife.personalfinancesapi.exception.MyFinancesException;
import io.programminglife.personalfinancesapi.security.CurrentUser;
import io.programminglife.personalfinancesapi.security.UserPrincipal;
import io.programminglife.personalfinancesapi.service.CategoryService;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/save")
    public ResponseEntity<Category> save(@RequestBody Category category) {
        return ResponseEntity.ok().body(categoryService.saveCategory(category));
    }

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.ok().body(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable(value = "id") Long id) {
        try {
            return ResponseEntity.ok().body(categoryService.findCategoryById(id));
        } catch (MyFinancesException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        categoryService.deleteCategory(id);
    }

    @GetMapping("/totalMonthlyAmountPerCategory/{year}/{month}")
    public ResponseEntity<List<PriceForCategory>> findTotalMonthlyAmountPerCategory(
            @CurrentUser UserPrincipal currentUser, @PathVariable(value = "year") Integer year,
            @PathVariable(value = "month") Integer month) {
        return ResponseEntity.ok().body(categoryService.findTotalMonthlyAmountPerCategory(year, month, currentUser.getId()));
    }

}
