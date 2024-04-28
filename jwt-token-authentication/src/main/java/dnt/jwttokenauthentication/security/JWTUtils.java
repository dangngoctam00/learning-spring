package dnt.jwttokenauthentication.security;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dnt.jwttokenauthentication.exception.JwtInvalidException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.regex.Pattern;

@Slf4j
@Component
public class JWTUtils {

    private static final String USER_SLUG_CLAIM = "sub";
    private static final String SECRET = "SECRET";


    public String getUserSlug(String token) {
        validateToken(token);
        String payload = StringUtils.split(token, ".")[1];
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String decodedPayload = new String(decoder.decode(payload));
        JsonObject jsonObject = JsonParser.parseString(decodedPayload).getAsJsonObject();
        return jsonObject.get(USER_SLUG_CLAIM).getAsString();
    }

    public void validateToken(String token) {
        try {
            if (!isValidToken(token)) {
                log.info("Token {} is invalid", token);
                throw new JwtInvalidException(token);
            }
        } catch (NoSuchAlgorithmException | InvalidKeyException ex) {
            log.info("Token {} is invalid, Exception: {}", token, ex.getMessage());
            throw new JwtInvalidException(token);
        }
    }

    // Validate with custom secret key
    private static boolean isValidToken(String extractedToken) throws NoSuchAlgorithmException, InvalidKeyException {
        final String[] tokenParts = extractedToken.split(Pattern.quote("."));
        String header = tokenParts[0];
        String payload = tokenParts[1];
        String signature = tokenParts[2];

        final byte[] calcHmacSha256 = calcHmacSha256(SECRET.getBytes(), (header+"."+payload).getBytes());

        final String s = Base64.getUrlEncoder().withoutPadding().encodeToString(calcHmacSha256);
        return signature.equals(s);
    }

    private static byte[] calcHmacSha256(byte[] secretKey, byte[] message)
                    throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] hmacSha256;
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, "HmacSHA256");
        mac.init(secretKeySpec);
        hmacSha256 = mac.doFinal(message);
        return hmacSha256;
    }

}
