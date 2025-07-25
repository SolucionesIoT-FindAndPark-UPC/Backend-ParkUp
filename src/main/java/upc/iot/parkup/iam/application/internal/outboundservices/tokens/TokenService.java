package upc.iot.parkup.iam.application.internal.outboundservices.tokens;

import org.springframework.security.core.Authentication;


public interface TokenService {
    String generateToken(Authentication authentication);

    String generateToken(String username);
    String getUsernameFromToken(String token);
    boolean validateToken(String token);
}
