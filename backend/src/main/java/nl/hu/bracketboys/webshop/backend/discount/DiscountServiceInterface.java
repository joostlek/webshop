package nl.hu.bracketboys.webshop.backend.discount;

import java.util.List;

public interface DiscountServiceInterface {
    List<Discount> getAllDiscounts();

    Discount getDiscountById(Long id);

    void delete(Long id);

    Discount save(Discount discount);
}
