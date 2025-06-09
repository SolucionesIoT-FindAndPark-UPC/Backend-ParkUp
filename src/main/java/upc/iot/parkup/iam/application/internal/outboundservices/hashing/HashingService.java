package upc.iot.parkup.iam.application.internal.outboundservices.hashing;

<<<<<<< Updated upstream
/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
=======
>>>>>>> Stashed changes
public interface HashingService {
    String encode(CharSequence rawPassword);
    boolean matches(CharSequence rawPassword, String encodedPassword);
}
