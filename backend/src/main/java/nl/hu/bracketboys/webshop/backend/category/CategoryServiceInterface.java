package nl.hu.bracketboys.webshop.backend.category;

import java.util.List;

public interface CategoryServiceInterface {
    List<Category> getAllCategories();

    Category getCategory(Long categoryId);

    Category saveCategory(Category category);

    Category updateCategory(Category category);

    void deleteCategory(Long categoryId);
}
