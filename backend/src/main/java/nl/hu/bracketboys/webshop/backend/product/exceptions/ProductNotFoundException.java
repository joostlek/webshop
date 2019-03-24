package nl.hu.bracketboys.webshop.backend.product.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long productId) {
        super(String.format("GetProduct with id %d not found", productId));
    }
}