package nl.hu.bracketboys.webshop.backend.category.exceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Long companyId) {
        super(String.format("Company %d not found", companyId));
    }
}
