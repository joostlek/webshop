package nl.hu.bracketboys.webshop.backend.product.controllers;

import nl.hu.bracketboys.webshop.backend.product.Product;
import nl.hu.bracketboys.webshop.backend.product.dto.ProductDTO;
import nl.hu.bracketboys.webshop.backend.product.service.ProductServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@BasePathAwareController
public class ProductController {
    private final ProductServiceInterface productService;

    private final ModelMapper modelMapper;

    @Autowired
    public ProductController(ModelMapper modelMapper, ProductServiceInterface productService) {
        this.modelMapper = modelMapper;
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<ProductDTO> getAllProducts() {
        return this.productService.getAllProducts()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/products")
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        return convertToDTO(productService.save(product));
    }

    @GetMapping("/products/{productId}")
    public ProductDTO getSingleProduct(@PathVariable Long productId) {
        return convertToDTO(productService.getProductById(productId));
    }

    @GetMapping("/categories/{categoryId}/products")
    public List<ProductDTO> getAllProductsByCategoryId(@PathVariable Long categoryId) {
        return this.productService.getAllProductsByCategoryId(categoryId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        if (productDTO.getDiscount() != null) {
            if (!(productDTO.getDiscount().getBeginDate().before(new Date()) && productDTO.getDiscount().getEndDate().after(new Date()))) {
                productDTO.setDiscount(null);
            }
        }
        return productDTO;
    }

    private Product convertToEntity(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }
}
