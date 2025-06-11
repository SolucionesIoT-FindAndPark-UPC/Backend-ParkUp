package upc.iot.parkup.iam.infrastructure.hashing.bcrypt;

import org.springframework.security.crypto.password.PasswordEncoder;
import upc.iot.parkup.iam.application.internal.outboundservices.hashing.HashingService;


public interface BCryptHashingService extends HashingService, PasswordEncoder {

}