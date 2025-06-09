package upc.iot.parkup.iam.application.internal.outboundservices.tokens;

import org.springframework.security.core.Authentication;

<<<<<<< Updated upstream
/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
=======
>>>>>>> Stashed changes
public interface TokenService {
    String generateToken(Authentication authentication);

    String generateToken(String username);
    String getUsernameFromToken(String token);
    boolean validateToken(String token);
}
