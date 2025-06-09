package upc.iot.parkup.iam.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import upc.iot.parkup.iam.domain.model.entities.Role;
import upc.iot.parkup.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

<<<<<<< Updated upstream
/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
=======
>>>>>>> Stashed changes
@Entity
public class User extends AuditableAbstractAggregateRoot<User> {

    @NotBlank
    @Size(max = 50)
    @Column(name = "name")
    private String name;


    @NotBlank
    @Size(max = 50)
    @Column(name = "lastname")
    private String lastname;


    @NotBlank
    @Size(max = 50)
    @Column(unique = true, name = "username")
    private String username;


    @NotBlank
    @Size(max = 120)
    @Column(name = "email")

    private String email;


    @NotBlank
    @Size(max = 120)
    @Column(name = "password")

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User() { this.roles = new HashSet<>(); }

    public User(String name, String lastname, String username, String email, String password) {
        this();
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;

    }

    public User(String name, String lastname, String username, String email, String password,Set<Role> roles) {
        this(name, lastname, username,email,  password);
        addRoles(roles);
    }

    public User addRole(Role role) {
        this.roles.add(role);
        return this;
    }

    public User addRoles(Set<Role> roles) {
        var validatedRoleSet = Role.validateRoleSet(roles);
        this.roles.addAll(validatedRoleSet);
        return this;
    }
    public String getEmail() {
        return this.email;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
    public String getLastname() {
        return lastname;
    }
    public Set<Role> getRoles() {
        return roles;
    }

}