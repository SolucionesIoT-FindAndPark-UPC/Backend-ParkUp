package upc.iot.parkup.iam.infrastructure.hashing.bcrypt;

import org.springframework.security.crypto.password.PasswordEncoder;
import upc.iot.parkup.iam.application.internal.outboundservices.hashing.HashingService;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
public interface BCryptHashingService extends HashingService, PasswordEncoder {

}