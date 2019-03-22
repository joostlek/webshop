package nl.hu.bracketboys.webshop.backend.discount;

import nl.hu.bracketboys.webshop.backend.discount.dto.DiscountDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DiscountController {
    private final DiscountServiceInterface discountService;

    private final ModelMapper modelMapper;

    @Autowired
    public DiscountController(ModelMapper modelMapper, DiscountServiceInterface discountService) {
        this.modelMapper = modelMapper;
        this.discountService = discountService;
    }

    @GetMapping("/discounts")
    public List<DiscountDTO> getAllProducts() {
        return this.discountService.getAllDiscounts()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/discounts")
    public DiscountDTO addProduct(@RequestBody DiscountDTO discountDTO) {
        Discount discount = convertToEntity(discountDTO);
        return convertToDTO(discountService.save(discount));
    }

    @GetMapping("/discounts/{discountId}")
    public DiscountDTO getSingleDiscount(@PathVariable Long discountId) {
        return convertToDTO(discountService.getDiscountById(discountId));
    }

    private DiscountDTO convertToDTO(Discount discount) {
        return modelMapper.map(discount, DiscountDTO.class);
    }

    private Discount convertToEntity(DiscountDTO discountDTO) {
        return modelMapper.map(discountDTO, Discount.class);
    }
}
