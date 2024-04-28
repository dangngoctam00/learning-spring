package dnt.jwttokenauthentication.api;

import dnt.jwttokenauthentication.dto.LoginRequest;
import dnt.jwttokenauthentication.dto.LoginResponse;
import dnt.jwttokenauthentication.dto.RegisterRequest;
import dnt.jwttokenauthentication.dto.RegisterResponse;
import dnt.jwttokenauthentication.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth/")
public class AuthenticationRestController {
    private AuthService authService;

    @Autowired
    public AuthenticationRestController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.login(loginRequest);
        if (!response.getAccess_token().equals("")) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerRequest) {
         RegisterResponse response = authService.register(registerRequest);
         if (response.getMessage().equals("Username is already exist!")) {
             return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
         }
         return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
