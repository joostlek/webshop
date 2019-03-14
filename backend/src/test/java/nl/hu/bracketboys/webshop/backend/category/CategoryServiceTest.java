package nl.hu.bracketboys.webshop.backend.category;

import nl.hu.bracketboys.webshop.backend.category.exceptions.CategoryNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@DisplayName("Category Service")
@Tag("services")
class CategoryServiceTest {

    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryServiceInterface categoryService;

    private Category category = new Category();

    private Category category1 = new Category();

    @BeforeEach
    void setUp() {
        category.setId(1L);
        category.setName("Laptops");
        category1.setId(2L);
        category.setName("Computers");
    }

    @Test
    @DisplayName("Get all categories")
    void getAllCategories() {
        when(categoryRepository.findAll()).thenReturn(Arrays.asList(category, category1));

        assertThat(categoryService.getAllCategories())
                .hasSize(2)
                .contains(category)
                .contains(category1);
    }

    @Test
    @DisplayName("Get category by id")
    void shouldReturnCategory_whenGetCategoryById() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        assertThat(categoryService.getCategory(1L))
                .isInstanceOf(Category.class)
                .isEqualTo(category);
    }

    @Test
    @DisplayName("Throw exception when get unknown category")
    void shouldThrowException_whenGetUnknownCategoryById() {
        assertThrows(CategoryNotFoundException.class,
                () -> categoryService.getCategory(3L)
        );
    }

    @Test
    @DisplayName("Delete category")
    void shouldCallDelete_whenDeleteCategory() {
        categoryService.deleteCategory(1L);

        verify(categoryRepository, atLeastOnce()).deleteById(any(Long.class));
    }

    @Test
    @DisplayName("Save category")
    void shouldReturnNewCategory_whenSaveCompany() {
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        assertThat(categoryService.saveCategory(category))
                .isEqualTo(category);
    }

    @Test
    @DisplayName("Update category")
    void shouldReturnUpdatedCategory_whenUpdateCategory() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(categoryRepository.save(any(Category.class))).thenReturn(category1);

        assertThat(categoryService.updateCategory(category))
                .isEqualTo(category1);
    }

    @Test
    @DisplayName("Throw exception when update invalid category")
    void shouldThrowException_whenUpdateInvalidCategory() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class,
                () -> categoryService.updateCategory(category)
        );
    }

    @TestConfiguration
    static class CategoryServiceTestContextConfiguration {
        @Autowired
        private CategoryRepository categoryRepository;

        @Bean
        public CategoryServiceInterface categoryServiceInterface() {
            return new CategoryService(categoryRepository);
        }
    }
}