package nl.hu.bracketboys.webshop.backend.product;

import nl.hu.bracketboys.webshop.backend.product.dto.ProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {
    private final ProductServiceInterface productService;

    private final ModelMapper modelMapper;

    @Autowired
    public ProductController(ModelMapper modelMapper, ProductServiceInterface productService) {
        this.modelMapper = modelMapper;
        this.productService = productService;
    }

    @GetMapping("/users")
    public List<ProductDTO> getAllProducts() {
        return this.productService.getAllProducts()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/users")
    public ProductDTO addUser(@RequestBody ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        return convertToDTO(productService.save(product));
    }

    @GetMapping("/users/{userId}")
    public ProductDTO getSingleUsers(@PathVariable Long userId) {
        return convertToDTO(productService.getProductById(userId));
    }

    private ProductDTO convertToDTO(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

    private Product convertToEntity(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }
}
