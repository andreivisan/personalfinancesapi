package io.programminglife.personalfinancesapi.controller;

import io.programminglife.personalfinancesapi.entity.Category;
import io.programminglife.personalfinancesapi.entity.dashboard.TotalAmountForCategory;
import io.programminglife.personalfinancesapi.exception.MyFinancesException;
import io.programminglife.personalfinancesapi.security.CurrentUser;
import io.programminglife.personalfinancesapi.security.UserPrincipal;
import io.programminglife.personalfinancesapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        List<Category> allCategories = (List<Category>)categoryService.findAll();
        return ResponseEntity.ok().body(allCategories);
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
    public ResponseEntity<List<TotalAmountForCategory>> findTotalMonthlyAmountPerCategory(
            @CurrentUser UserPrincipal currentUser, @PathVariable(value = "year") Integer year,
            @PathVariable(value = "month") Integer month) {
        try {
            return ResponseEntity.ok().body(categoryService.findTotalMonthlyAmountPerCategory(year, month, currentUser.getId()));
        } catch (MyFinancesException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
