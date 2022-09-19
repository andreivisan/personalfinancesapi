package io.programminglife.personalfinancesapi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import io.programminglife.personalfinancesapi.entity.Category;

@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void test_no_categories_if_repository_is_empty() {
        List<Category> categories = categoryRepository.findAll();

        assertEquals(0, categories.size());
    }

    @Test
    public void test_save_category() {
        Category testCategory = categoryRepository.save(new Category("groceries"));

        assertNotNull(testCategory);
        assertEquals("groceries", testCategory.getLabel());
    }

    @Test
    public void test_get_category_by_id() {
        Category testCategory = categoryRepository.save(new Category("groceries"));

        assertNotNull(testCategory);
    }

    @Test
    public void test_get_all_categories() {
        categoryRepository.save(new Category("groceries"));
        categoryRepository.save(new Category("food"));

        List<Category> categories = categoryRepository.findAll();

        assertNotNull(categories);
        assertEquals(2, categories.size());
    }

    @Test
    public void test_delete_category_by_id() {
        Category testCategory = categoryRepository.save(new Category("categories"));

        categoryRepository.deleteById(testCategory.getId());

        List<Category> categories = categoryRepository.findAll();

        assertEquals(0, categories.size());
    }

}
