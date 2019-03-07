package nl.hu.bracketboys.webshop.backend.user;

import java.util.List;

public interface UserServiceInterface {
    List<User> getAllUsers();

    User getUserById(Long id);

    User getUserByEmail(String email);

    void delete(Long id);

    User save(User user);
}
