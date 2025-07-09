package upc.iot.parkup.edgenodes.domain.model.aggregates;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import upc.iot.parkup.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
public class EdgeNode extends AuditableAbstractAggregateRoot<EdgeNode> {

    @NotBlank
    @Size(max = 255)
    @Column(name = "url", nullable = false)
    private String url;

    @NotNull
    @Column(name = "port", nullable = false)
    private Integer port;

    // Default constructor for JPA
    public EdgeNode() {}

    // Constructor without ID
    public EdgeNode(String url, Integer port) {
        this.url = url;
        this.port = port;
    }

    // Getters
    public String getUrl() {
        return url;
    }

    public Integer getPort() {
        return port;
    }

    public EdgeNode updateUrlAndPort(String url, Integer port) {
        this.url = url;
        this.port = port;
        return this;
    }
}

