package nl.hu.bracketboys.webshop.backend.category.service;

import nl.hu.bracketboys.webshop.backend.category.Category;

import java.util.List;

public interface CategoryServiceInterface {
    List<Category> getAllCategories();

    Category getCategory(Long categoryId);

    Category saveCategory(Category category);

    Category updateCategory(Category category);

    void deleteCategory(Long categoryId);
}
