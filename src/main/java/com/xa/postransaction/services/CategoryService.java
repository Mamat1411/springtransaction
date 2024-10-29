package com.xa.postransaction.services;

import java.util.List;

import com.xa.postransaction.entities.Category;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    void saveCategory(Category category);
    void deleteCategory(Long id);
}
