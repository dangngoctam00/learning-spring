package dnt.jwttokenauthentication.exception;

public class JwtInvalidException extends RuntimeException{
    private final String jwt;

    public JwtInvalidException(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public String getMessage() {
        return String.format("Jwt '%s' is invalid", jwt);
    }
}
