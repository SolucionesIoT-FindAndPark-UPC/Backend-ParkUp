package upc.iot.parkup.iam.domain.model.entities;

import jakarta.persistence.*;


@Entity
public class AllowedDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String domain;


    public void setId(Long id) {
        this.id = id;
    }
    public void setDomain(String domain) {
        this.domain = domain;
    }
    public String getDomain() {
        return domain;
    }
    public Long getId() {
        return id;
    }
}