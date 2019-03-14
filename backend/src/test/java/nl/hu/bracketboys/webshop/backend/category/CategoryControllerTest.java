package nl.hu.bracketboys.webshop.backend.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hu.bracketboys.webshop.backend.category.dto.NewCategoryDTO;
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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CategoryController.class)
@DisplayName("User Controller")
@Tag("Controller")
class CategoryControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CategoryServiceInterface categoryService;

    private Category category = new Category();

    private Category category1 = new Category();

    @BeforeEach
    void setUp() {
        category.setId(1L);
        category.setName("laptops");
        category1.setId(2L);
        category1.setName("computers");
    }

    @Test
    @DisplayName("Get single category")
    void shouldReturnObject_whenGetSingleCategory() throws Exception {

        given(categoryService.getCategory(1L)).willReturn(category);

        mvc.perform(get("/categories/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(category.getName())));
    }

    @Test
    @DisplayName("Get all categories")
    void shouldReturnListOfObject_whenGetAllCategories() throws Exception {

        given(categoryService.getAllCategories()).willReturn(Arrays.asList(category, category1));

        mvc.perform(get("/categories")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }


    @Test
    @DisplayName("Add category")
    void shouldReturnNewCategory_whenSaveCategory() throws Exception {

        given(categoryService.saveCategory(any(Category.class))).willReturn(category);

        NewCategoryDTO categoryDTO = new NewCategoryDTO();
        categoryDTO.setName(category.getName());

        mvc.perform(post("/categories")
                .content(objectMapper.writer().writeValueAsString(categoryDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(category.getId().intValue())))
                .andExpect(jsonPath("$.name", is(category.getName())));
    }

    @Test
    @DisplayName("Update category")
    void shouldReturnUpdatedCategory_whenUpdateCategory() throws Exception {

        given(categoryService.updateCategory(any(Category.class))).willReturn(category);

        NewCategoryDTO categoryDTO = new NewCategoryDTO();
        categoryDTO.setName(category1.getName());

        mvc.perform(put("/categories/1")
                .content(objectMapper.writer().writeValueAsString(categoryDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(category.getId().intValue())))
                .andExpect(jsonPath("$.name", is(category.getName())));
    }

    @Test
    @DisplayName("Delete category")
    void shouldCallDelete_whenDeleteCategory() throws Exception {
        mvc.perform(delete("/categories/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(categoryService, atLeastOnce()).deleteCategory(1L);
    }
}