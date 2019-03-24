package nl.hu.bracketboys.webshop.backend.product;

import nl.hu.bracketboys.webshop.backend.category.service.CategoryServiceInterface;
import nl.hu.bracketboys.webshop.backend.product.exceptions.ProductNotFoundException;
import nl.hu.bracketboys.webshop.backend.product.repository.ProductRepository;
import nl.hu.bracketboys.webshop.backend.product.service.ProductService;
import nl.hu.bracketboys.webshop.backend.product.service.ProductServiceInterface;
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
@DisplayName("GetProduct Service")
@Tag("services")
class ProductServiceTest {

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceInterface productService;

    @MockBean
    private CategoryServiceInterface categoryService;

    private Product product = new Product();

    private Product product1 = new Product();

    @BeforeEach
    void setUp() {
        product.setId(1L);
        product.setTitle("product 1");
        product.setDescription("Generiek product #1.");
        product.setPrice(25.0);
        product.setId(2L);
        product1.setTitle("product 2");
        product1.setDescription("Generiek product #2.");
        product1.setPrice(5.0);
    }

    @Test
    void getAllUsers() {
        when(productRepository.findAll()).thenReturn(Arrays.asList(product, product1));

        assertThat(productService.getAllProducts())
                .hasSize(2)
                .contains(product)
                .contains(product1);
    }

    @Test
    void getUserById() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        assertThat(productService.getProductById(1L))
                .isInstanceOf(Product.class)
                .isEqualTo(product);
    }

    @Test
    void throwException_whenGetUnknownUserById() {
        assertThrows(ProductNotFoundException.class,
                () -> productService.getProductById(3L)
        );
    }

    @Test
    void delete() {
        productService.delete(1L);

        verify(productRepository, atLeastOnce()).deleteById(any(Long.class));
    }

    @Test
    void save() {
        when(productRepository.save(any(Product.class))).thenReturn(product);

        assertThat(productService.save(product))
                .isEqualTo(product);
    }

    @TestConfiguration
    static class UserServiceTestContextConfiguration {
        @Autowired
        private ProductRepository productRepository;

        @Autowired
        private CategoryServiceInterface categoryService;

        @Bean
        public ProductServiceInterface productServiceInterface() {
            return new ProductService(productRepository, categoryService);
        }
    }
}