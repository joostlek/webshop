package nl.hu.bracketboys.webshop.backend.address;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hu.bracketboys.webshop.backend.security.JwtTokenProvider;
import nl.hu.bracketboys.webshop.backend.user.UserServiceInterface;
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
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AddressController.class)
@DisplayName("Address Controller")
@Tag("Controller")
class AddressControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AddressServiceInterface addressService;

    @MockBean
    private UserServiceInterface userService;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    private Address address = new Address();

    private Address address2 = new Address();

    @BeforeEach
    void setUp() {
        address.setId(1L);
        address.setStreet("Heidelberglaan");
        address.setHouseNumber("15");
        address.setZipCode("3481CS");
        address.setCountry("Netherlands");
        address.setCity("Utrecht");
        address2.setId(2L);
        address2.setStreet("Padualaan");
        address2.setHouseNumber("101");
        address2.setZipCode("3481CS");
        address2.setCountry("Netherlands");
        address2.setCity("Utrecht");
    }


    @Test
    @DisplayName("Get single address")
    void shouldReturnListOfAddresses_whenGet() throws Exception {

        given(addressService.getAddressesByUserId(1L)).willReturn(Arrays.asList(address, address2));

        mvc.perform(get("/users/1/addresses")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }


    @Test
    @DisplayName("Add address")
    void shouldSaveUser_whenSaveUser() throws Exception {

        given(addressService.save(any(Address.class))).willReturn(address);

        mvc.perform(post("/users/1/addresses")
                .content(objectMapper.writer().writeValueAsString(address))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.street", is(address.getStreet())))
                .andExpect(jsonPath("$.houseNumber", is(address.getHouseNumber())))
                .andExpect(jsonPath("$.zipCode", is(address.getZipCode())))
                .andExpect(jsonPath("$.city", is(address.getCity())))
                .andExpect(jsonPath("$.country", is(address.getCountry())));
    }


    @Test
    @DisplayName("Update Address")
    void shouldUpdateAddress_whenUpdateAddress() throws Exception {

        given(addressService.update(any(Address.class))).willReturn(address);

        mvc.perform(put("/addresses/1")
                .content(objectMapper.writer().writeValueAsString(address))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.street", is(address.getStreet())))
                .andExpect(jsonPath("$.houseNumber", is(address.getHouseNumber())))
                .andExpect(jsonPath("$.zipCode", is(address.getZipCode())))
                .andExpect(jsonPath("$.city", is(address.getCity())))
                .andExpect(jsonPath("$.country", is(address.getCountry())));
    }


    @Test
    @DisplayName("Delete address")
    void shouldDeleteUser_whenDeleteUser() throws Exception {
        mvc.perform(delete("/addresses/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(addressService, atLeastOnce()).delete(1L);
    }

}