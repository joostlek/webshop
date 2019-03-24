package nl.hu.bracketboys.webshop.backend.discount.service;

import nl.hu.bracketboys.webshop.backend.discount.Discount;

import java.util.List;

public interface DiscountServiceInterface {
    List<Discount> getAllDiscounts();

    Discount getDiscountById(Long id);

    void delete(Long id);

    Discount save(Discount discount);
}
