package nl.hu.bracketboys.webshop.backend.address;

import nl.hu.bracketboys.webshop.backend.address.exceptions.AddressNotFoundException;
import nl.hu.bracketboys.webshop.backend.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@DisplayName("User Service")
@Tag("services")
class AddressServiceTest {

    @MockBean
    private AddressRepository addressRepository;

    @Autowired
    private AddressServiceInterface addressService;

    private User user = new User();

    private Address address = new Address();

    private Address address2 = new Address();

    @BeforeEach
    void setUp() {
        user.setFirstName("Alex");
        user.setLastName("Jones");
        user.setEmail("Alex.jones@alex.com");
        user.setActive(true);
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
    @DisplayName("Get list of addresses by user ID")
    void shouldReturnAddresses_whenGetAddressesByUserId() {
        when(addressRepository.getAddressesByUserId(1L)).thenReturn(Arrays.asList(address, address2));

        assertThat(addressService.getAddressesByUserId(1L))
                .hasSize(2)
                .contains(address)
                .contains(address2);
    }

    @Test
    @DisplayName("Throw exception when get invalid ID")
    void shouldThrowAddressNotFoundException_whenGetInvalidAddress() {
        assertThrows(AddressNotFoundException.class,
                () -> addressService.getAddressById(3L)
        );
    }

    @Test
    @DisplayName("Get single address")
    void shouldReturnAddress_whenGetAddressById() {
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));

        assertThat(addressService.getAddressById(1L))
                .isInstanceOf(Address.class)
                .isEqualTo(address);
    }

    @Test
    @DisplayName("Delete address")
    void delete() {
        addressService.delete(1L);

        verify(addressRepository, atLeastOnce()).deleteById(any(Long.class));
    }

    @Test
    @DisplayName("Save address")
    void save() {
        when(addressRepository.save(any(Address.class))).thenReturn(address);

        assertThat(addressService.save(address))
                .isEqualTo(address);
    }

    @Test
    @DisplayName("Update address")
    void shouldReturnUpdatedAddress_whenUpdateAddress() {
        when(addressRepository.findById(2L)).thenReturn(Optional.of(address));
        when(addressRepository.save(any(Address.class))).thenReturn(address2);

        assertThat(addressService.update(address2))
                .isEqualTo(address2);
    }

    @Test
    @DisplayName("Update invalid address")
    void shouldThrowAddressNotFoundException_whenUpdateInvalidAddress() {
        when(addressRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(AddressNotFoundException.class,
                () -> addressService.update(address2)
        );
    }

    @TestConfiguration
    static class AddressServiceTestContextConfiguration {
        @Autowired
        private AddressRepository addressRepository;

        @Bean
        public AddressServiceInterface addressServiceInterface() {
            return new AddressService(addressRepository);
        }
    }

}