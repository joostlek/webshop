package nl.hu.bracketboys.webshop.backend.product;

import nl.hu.bracketboys.webshop.backend.category.CategoryServiceInterface;
import nl.hu.bracketboys.webshop.backend.product.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product save(Product product) {
        product.addCategory(categoryService.getCategory(1L));
        return this.saveProduct(product);
    }

    @Override
    public List<Product> getAllProductsByCategoryId(Long categoryId) {
        return productRepository.getAllByCategoryId(categoryId);
    }

    private Product saveProduct(Product product) {
        if (product.getPrice() < 0) {
            throw new RuntimeException("Price is below zero");
        }
        return this.productRepository.save(product);
    }
}
