package nl.hu.bracketboys.webshop.backend.product.service;

import nl.hu.bracketboys.webshop.backend.category.service.CategoryServiceInterface;
import nl.hu.bracketboys.webshop.backend.product.Product;
import nl.hu.bracketboys.webshop.backend.product.exceptions.ProductNotFoundException;
import nl.hu.bracketboys.webshop.backend.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements ProductServiceInterface {
    private final ProductRepository productRepository;

    private final CategoryServiceInterface categoryService;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryServiceInterface categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product removeProductFromStorage(Long productId, int amount) {
        return this.getProductById(productId);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Product save(Product product) {
        product.addCategory(categoryService.getCategory(1L));
        return this.saveProduct(product);
    }

    @Override
    public List<Product> getAllProductsByCategoryId(Long categoryId) {
        return productRepository.getAllByCategoryId(categoryId);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Product updateProduct(Product product) {
        Product newProduct = this.getProductById(product.getId());
        newProduct.setDescription(product.getDescription());
        newProduct.setPrice(product.getPrice());
        newProduct.setTitle(product.getTitle());
        return this.saveProduct(product);
    }

    private Product saveProduct(Product product) {
        if (product.getPrice() < 0) {
            throw new RuntimeException("Price is below zero");
        }
        return this.productRepository.save(product);
    }
}
