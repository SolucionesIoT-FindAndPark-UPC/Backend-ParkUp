package upc.iot.parkup.iam.infrastructure.authorization.sfs.model;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

<<<<<<< Updated upstream
/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
=======

>>>>>>> Stashed changes

public class UsernamePasswordAuthenticationTokenBuilder {
    public static UsernamePasswordAuthenticationToken build(UserDetails principal, HttpServletRequest request) {
        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                principal,
                null,
                principal.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request));
        return usernamePasswordAuthenticationToken;
    }
}