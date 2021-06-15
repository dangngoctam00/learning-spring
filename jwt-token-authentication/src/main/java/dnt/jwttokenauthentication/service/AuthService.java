package dnt.jwttokenauthentication.service;

import dnt.jwttokenauthentication.domain.User;
import dnt.jwttokenauthentication.dto.LoginRequest;
import dnt.jwttokenauthentication.dto.LoginResponse;
import dnt.jwttokenauthentication.dto.RegisterRequest;
import dnt.jwttokenauthentication.dto.RegisterResponse;
import dnt.jwttokenauthentication.repository.UserRepository;
import dnt.jwttokenauthentication.security.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private UserRepository userRepository;
    private JwtTokenUtils jwtTokenUtils;
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(UserRepository userRepository, JwtTokenUtils jwtTokenUtils, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtTokenUtils = jwtTokenUtils;
        this.authenticationManager = authenticationManager;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null) {
            return new LoginResponse(jwtTokenUtils.generateAccessToken(user), "Login successfully!");
        }
        return new LoginResponse("", "Username or password is incorrect!");
    }

    public RegisterResponse register(RegisterRequest registerRequest) {
        User user = userRepository.findByUsername(registerRequest.getUsername()).orElse(null);
        if (user != null) {
            return new RegisterResponse("Username is already exist!");
        }
        user = new User(registerRequest.getUsername(), registerRequest.getPassword());
        userRepository.save(user);
        return new RegisterResponse("Register successfully!");
    }
}
