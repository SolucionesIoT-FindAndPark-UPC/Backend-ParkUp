package upc.iot.parkup.iam.interfaces.rest.resources;

import java.util.List;


public record SignUpResource(String name, String lastname, String username, String email, String password, List<String> roles) {
}
