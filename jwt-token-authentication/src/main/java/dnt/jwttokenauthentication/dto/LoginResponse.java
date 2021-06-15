package dnt.jwttokenauthentication.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String access_token;
    private String token_type = "Bearer";
    private String message;

    public LoginResponse(String access_token, String message) {
        this.access_token = access_token;
        this.message = message;
    }
}
