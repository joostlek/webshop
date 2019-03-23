package nl.hu.bracketboys.webshop.backend.discount.exceptions;

public class DiscountNotFoundException extends RuntimeException {
    public DiscountNotFoundException(Long discountId) {
        super(String.format("Discount with id %d not found", discountId));
    }
}