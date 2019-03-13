package nl.hu.bracketboys.webshop.backend.category;

import nl.hu.bracketboys.webshop.backend.category.dto.CategoryDTO;
import nl.hu.bracketboys.webshop.backend.category.dto.NewCategoryDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CategoryController {

    private final CategoryServiceInterface categoryService;

    private final ModelMapper modelMapper;

    @Autowired
    public CategoryController(CategoryServiceInterface categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/categories")
    public List<CategoryDTO> getAllCategory() {
        return categoryService.getAllCategories()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/categories/{categoryId}")
    public CategoryDTO getSingleCategory(@PathVariable Long categoryId) {
        return convertToDTO(categoryService.getCategory(categoryId));
    }

    @PostMapping("/categories")
    public CategoryDTO addCategory(@RequestBody NewCategoryDTO categoryDTO) {
        Category category = convertToEntity(categoryDTO);
        return convertToDTO(categoryService.saveCategory(category));
    }

    @PutMapping("/categories/{categoryId}")
    public CategoryDTO updateCategory(@RequestBody NewCategoryDTO categoryDTO, @PathVariable Long categoryId) {
        Category category = convertToEntity(categoryDTO);
        category.setId(categoryId);
        return convertToDTO(categoryService.updateCategory(category));
    }

    @DeleteMapping("/categories/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }

    private CategoryDTO convertToDTO(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

    private Category convertToEntity(NewCategoryDTO categoryDTO) {
        return modelMapper.map(categoryDTO, Category.class);
    }
}
