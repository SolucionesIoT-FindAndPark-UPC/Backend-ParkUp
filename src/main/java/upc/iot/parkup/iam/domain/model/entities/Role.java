package upc.iot.parkup.iam.domain.model.entities;

import jakarta.persistence.*;
import lombok.*;
import upc.iot.parkup.iam.domain.model.valueobjects.Roles;

import java.util.Set;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
@Entity
@Data
@AllArgsConstructor
@With
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true, length = 20)
    private Roles name;
    public Role() { }
    public Role(Roles name) {
        this.name = name;
    }

    public String getStringName() {
        return name.name();
    }

    public static Role getDefaultRole() {
        return new Role(Roles.ROLE_DRIVER);
    }

    public static Role toRoleFromName(String name) {
        return new Role(Roles.valueOf(name));
    }

    public static Set<Role> validateRoleSet(Set<Role> roles) {
        if (roles == null || roles.isEmpty()) {
            return Set.of(getDefaultRole());
        }
        return roles;
    }
    public Roles getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
