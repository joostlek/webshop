package nl.hu.bracketboys.webshop.backend.user;

import nl.hu.bracketboys.webshop.backend.user.exceptions.UserNotFoundException;
import nl.hu.bracketboys.webshop.backend.user.repository.UserRepository;
import nl.hu.bracketboys.webshop.backend.user.service.UserService;
import nl.hu.bracketboys.webshop.backend.user.service.UserServiceInterface;
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
class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
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
    void getAllUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user, user1));

        assertThat(userService.getAllUsers())
                .hasSize(2)
                .contains(user)
                .contains(user1);
    }

    @Test
    void getUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        assertThat(userService.getUserById(1L))
                .isInstanceOf(User.class)
                .isEqualTo(user);
    }

    @Test
    void throwException_whenGetUnknownUserById() {
        assertThrows(UserNotFoundException.class,
                () -> userService.getUserById(3L)
        );
    }

    @Test
    void getUserByEmail() {
        when(userRepository.findUserByEmail(any(String.class))).thenReturn(Optional.of(user));

        assertThat(userService.getUserByEmail("Alex.jones@alex.com"))
                .isInstanceOf(User.class)
                .isEqualTo(user);
    }

    @Test
    void throwException_whenGetUnknownUserByEmail() {
        assertThrows(UserNotFoundException.class,
                () -> userService.getUserByEmail("Unknown@email.com")
        );
    }

    @Test
    void delete() {
        userService.delete(1L);

        verify(userRepository, atLeastOnce()).deleteById(any(Long.class));
    }

    @Test
    void save() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        assertThat(userService.save(user))
                .isEqualTo(user);
    }

    @TestConfiguration
    static class UserServiceTestContextConfiguration {
        @Autowired
        private UserRepository userRepository;

        @Bean
        public UserServiceInterface userServiceInterface() {
            return new UserService(userRepository);
        }
    }
}