package nl.hu.bracketboys.webshop.backend.security.auth;

import nl.hu.bracketboys.webshop.backend.security.auth.dto.SignInDTO;
import nl.hu.bracketboys.webshop.backend.security.auth.dto.TokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthServiceInterface authService;

    @Autowired
    public AuthController(AuthServiceInterface authService) {
        this.authService = authService;
    }

    @PostMapping("/users/signin")
    public TokenDTO login(@RequestBody SignInDTO signInDTO) {
        return new TokenDTO(authService.signin(signInDTO.getUsername(), signInDTO.getPassword()));
    }
}
