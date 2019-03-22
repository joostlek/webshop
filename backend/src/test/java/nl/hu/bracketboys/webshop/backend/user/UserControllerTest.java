package nl.hu.bracketboys.webshop.backend.user;

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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@DisplayName("User Controller")
@Tag("Controller")
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserServiceInterface userService;

    private User user = new User();

    private User user1 = new User();

    @BeforeEach
    void setUp() {
        user.setId(1L);
        user.setFirstName("Alex");
        user.setLastName("Jones");
        user.setEmail("Alex.jones@alex.com");
        user.setActive(true);
        user.setId(2L);
        user1.setFirstName("Jane");
        user1.setLastName("Joness");
        user1.setEmail("Jane.jones@alex.com");
        user1.setActive(true);
    }

    @Test
    @DisplayName("Get single user")
    void shouldReturnUserObject_whenGetUser() throws Exception {

        given(userService.getUserById(1L)).willReturn(user);

        mvc.perform(get("/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(user.getFirstName())));
    }

    @Test
    @DisplayName("Get all users")
    void shouldReturnListOfObject_whenGetUsers() throws Exception {

        given(userService.getAllUsers()).willReturn(Arrays.asList(user, user1));

        mvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }


    @Test
    @DisplayName("Add user")
    void shouldSaveUser_whenSaveUser() throws Exception {

        given(userService.save(any(User.class))).willReturn(user);

        mvc.perform(post("/users")
                .content(objectMapper.writer().writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(user.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(user.getLastName())))
                .andExpect(jsonPath("$.email", is(user.getEmail())))
                .andExpect(jsonPath("$.password", is(user.getPassword())))
                .andExpect(jsonPath("$.created", is(user.getCreated())))
                .andExpect(jsonPath("$.updated", is(user.getUpdated())))
                .andExpect(jsonPath("$.active", is(user.isActive())));
    }


//    @Test
//    @DisplayName("Update Address")
//    void shouldUpdateAddress_whenUpdateAddress() throws Exception {
//
//        given(addressService.update(any(Address.class))).willReturn(address);
//
//        mvc.perform(put("/addresses/1")
//                .content(objectMapper.writer().writeValueAsString(address))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.street", is(address.getStreet())))
//                .andExpect(jsonPath("$.houseNumber", is(address.getHouseNumber())))
//                .andExpect(jsonPath("$.zipCode", is(address.getZipCode())))
//                .andExpect(jsonPath("$.city", is(address.getCity())))
//                .andExpect(jsonPath("$.country", is(address.getCountry())));
//    }
//
//
//    @Test
//    @DisplayName("Delete address")
//    void shouldDeleteUser_whenDeleteUser() throws Exception {
//        mvc.perform(delete("/addresses/1")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//
//        verify(addressService, atLeastOnce()).delete(1L);
//    }


}