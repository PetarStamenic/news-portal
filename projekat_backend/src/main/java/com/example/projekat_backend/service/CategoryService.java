package com.example.projekat_backend.service;

import com.example.projekat_backend.model.Category;
import com.example.projekat_backend.repository.category.CategoryRepository;

import javax.inject.Inject;
import java.util.List;

public class CategoryService {
    @Inject
    private CategoryRepository categoryRepository;

    public List<Category> allCategory() {
        return this.categoryRepository.allCategory();
    }
    public Category findCategory(String name) {
        return this.categoryRepository.findCategory(name);
    }
    public Category addCategory(Category category) {
        return this.categoryRepository.addCategory(category);
    }
    public Category updateCategory(Category category, String name) { return this.categoryRepository.updateCategory(category, name); }
    public void deleteCategory(String name) {
        this.categoryRepository.deleteCategory(name);
    }
}
