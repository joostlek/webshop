package nl.hu.bracketboys.webshop.backend.category;

import nl.hu.bracketboys.webshop.backend.category.exceptions.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoryService implements CategoryServiceInterface {

    @Autowired
    private CategoryRepository categoryRepository;

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
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category newCategory) {
        return categoryRepository.findById(newCategory.getId())
                .map((category) -> {
                    category.setName(newCategory.getName());
                    return categoryRepository.save(category);
                })
                .orElseThrow(() -> new CategoryNotFoundException(newCategory.getId()));
    }

}
