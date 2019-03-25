package nl.hu.bracketboys.webshop.backend.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hu.bracketboys.webshop.backend.product.service.ProductServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductServiceInterface productService;

    private Product product = new Product();

    private Product product1 = new Product();

    @BeforeEach
    void setUp() {

    }

    @Test
    void getAllProducts() {
    }

    @Test
    void addProduct() {
    }

    @Test
    void getSingleProduct() {
    }
}