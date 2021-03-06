package nl.hu.bracketboys.webshop.backend.category.service;

import nl.hu.bracketboys.webshop.backend.category.Category;
import nl.hu.bracketboys.webshop.backend.category.exceptions.CategoryNotFoundException;
import nl.hu.bracketboys.webshop.backend.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements CategoryServiceInterface {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Category updateCategory(Category newCategory) {
        return categoryRepository.findById(newCategory.getId())
                .map(category -> {
                    category.setName(newCategory.getName());
                    return categoryRepository.save(category);
                })
                .orElseThrow(() -> new CategoryNotFoundException(newCategory.getId()));
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

}
