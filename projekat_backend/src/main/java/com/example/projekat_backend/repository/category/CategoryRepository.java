package com.example.projekat_backend.repository.category;

import com.example.projekat_backend.model.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> allCategory();
    Category findCategory(String name);
    Category addCategory(Category category);
    Category updateCategory(Category category, String name);
    void deleteCategory(String name);
}
