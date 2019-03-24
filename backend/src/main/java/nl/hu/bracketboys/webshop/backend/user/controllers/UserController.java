package nl.hu.bracketboys.webshop.backend.user.controllers;

import nl.hu.bracketboys.webshop.backend.user.User;
import nl.hu.bracketboys.webshop.backend.user.dto.NewUserDTO;
import nl.hu.bracketboys.webshop.backend.user.dto.UserDTO;
import nl.hu.bracketboys.webshop.backend.user.service.UserServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@BasePathAwareController
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
    public UserDTO addUser(@RequestBody NewUserDTO userDTO) {
        User user = convertToEntity(userDTO);
        return convertToDTO(userService.save(user));
    }

    @GetMapping("/users/me")
    public UserDTO getMe(Principal principal) {
        return convertToDTO(userService.getUserByEmail(principal.getName()));
    }

    @GetMapping("/users/{userId}")
    public UserDTO getSingleUsers(@PathVariable Long userId) {
        return convertToDTO(userService.getUserById(userId));
    }

    private UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    private User convertToEntity(NewUserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
