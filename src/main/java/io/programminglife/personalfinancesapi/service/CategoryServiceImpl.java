package io.programminglife.personalfinancesapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.programminglife.personalfinancesapi.entity.Category;
import io.programminglife.personalfinancesapi.exception.MyFinancesException;
import io.programminglife.personalfinancesapi.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryById(Long categoryId) throws MyFinancesException {
        return categoryRepository.findById(categoryId).orElseThrow(
                () -> new MyFinancesException(String.format("Category with id %s was not found!", categoryId)));
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

}
