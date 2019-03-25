package nl.hu.bracketboys.webshop.backend.discount.service;

import nl.hu.bracketboys.webshop.backend.discount.Discount;
import nl.hu.bracketboys.webshop.backend.discount.exceptions.DiscountNotFoundException;
import nl.hu.bracketboys.webshop.backend.discount.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountService implements DiscountServiceInterface {
    private final DiscountRepository discountRepository;

    @Autowired
    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @Override
    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll();
    }

    @Override
    public Discount getDiscountById(Long id) {
        return discountRepository.findById(id)
                .orElseThrow(() -> new DiscountNotFoundException(id));
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(Long id) {
        discountRepository.deleteById(id);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Discount save(Discount discount) {
        return discountRepository.save(discount);
    }
}
