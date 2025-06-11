package upc.iot.parkup.iam.infrastructure.tokens.jwt;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.TokenService;


public interface BearerTokenService extends TokenService {
    String generateToken(String username);

    String getBearerTokenFrom(HttpServletRequest request);

    boolean validateToken(String token);

    String getUsernameFromToken(String token);
}