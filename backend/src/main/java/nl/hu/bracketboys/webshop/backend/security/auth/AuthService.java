package nl.hu.bracketboys.webshop.backend.security.auth;

import nl.hu.bracketboys.webshop.backend.security.CustomException;
import nl.hu.bracketboys.webshop.backend.security.JwtTokenProvider;
import nl.hu.bracketboys.webshop.backend.user.User;
import nl.hu.bracketboys.webshop.backend.user.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthServiceInterface {

    private final UserServiceInterface userService;

    private final JwtTokenProvider jwtTokenProvider;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(UserServiceInterface userService, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            User user = userService.getUserByEmail(username);
            return jwtTokenProvider.createToken(username, user.getId(), user.getGrantedAuthorities());
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
