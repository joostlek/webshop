package nl.hu.bracketboys.webshop.backend.user;

import nl.hu.bracketboys.webshop.backend.user.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {
    private final UserServiceInterface userService;

    private final ModelMapper modelMapper;

    @Autowired
    public UserController(ModelMapper modelMapper, UserServiceInterface userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        return this.userService.getAllUsers()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO addUser(@RequestBody UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        return convertToDTO(userService.save(user));
    }

    @GetMapping("/users/{userId}")
    public UserDTO getSingleUsers(@PathVariable Long userId) {
        return convertToDTO(userService.getUserById(userId));
    }

    private UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    private User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
