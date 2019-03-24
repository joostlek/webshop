package nl.hu.bracketboys.webshop.backend.product;

import java.util.List;

public interface ProductServiceInterface {
    List<Product> getAllProducts();

    Product removeProductFromStorage(Long productId, int amount);

    Product getProductById(Long id);

    void delete(Long id);

    Product save(Product product);

    List<Product> getAllProductsByCategoryId(Long categoryId);
}
