package nl.hu.bracketboys.webshop.backend.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
@DisplayName("Product Controller")
@Tag("Controller")
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
        product.setId(1L);
        product.setTitle("product 1");
        product.setDescription("Generiek product #1.");
        product.setPrice(25.0);
        product1.setId(2L);
        product1.setTitle("product 2");
        product1.setDescription("Generiek product #2.");
        product1.setPrice(5.0);
    }

    @Test
    @DisplayName("Get single product")
    void shouldReturnProductObject_whenGetProduct() throws Exception {

        given(productService.getProductById(1L)).willReturn(product);

        mvc.perform(get("/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(product.getTitle())));

    }

    @Test
    @DisplayName("Get all products")
    void shouldReturnListOfProductObjects_whenGetProducts() throws Exception {

        given(productService.getAllProducts()).willReturn(Arrays.asList(product, product1));

        mvc.perform(get("/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }



    @Test
    @DisplayName("Add product")
    void shouldSaveProduct_whenSaveProduct() throws Exception {

        given(productService.save(any(Product.class))).willReturn(product);

        mvc.perform(post("/products")
                .content(objectMapper.writer().writeValueAsString(product))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is(product.getTitle())))
                .andExpect(jsonPath("$.description", is(product.getDescription())))
                .andExpect(jsonPath("$.price", is(product.getPrice())))
                .andExpect(jsonPath("$.created", is(product.getCreated())))
                .andExpect(jsonPath("$.updated", is(product.getUpdated())));
    }




}