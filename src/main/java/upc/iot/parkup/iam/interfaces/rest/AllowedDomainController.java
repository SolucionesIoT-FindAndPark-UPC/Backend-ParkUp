package upc.iot.parkup.iam.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import upc.iot.parkup.iam.domain.model.entities.AllowedDomain;
import upc.iot.parkup.iam.infrastructure.persistence.jpa.repositories.AllowedDomainRepository;

import java.util.List;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
@RestController
@RequestMapping("/api/admin/domains")
@Tag(name = "Domains", description = "Domain Management Endpoints")

public class AllowedDomainController {

    private final AllowedDomainRepository repository;

    public AllowedDomainController(AllowedDomainRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public AllowedDomain addDomain(@RequestBody String domain) {
        AllowedDomain d = new AllowedDomain();
        d.setDomain(domain.toLowerCase());
        return repository.save(d);
    }

    @GetMapping
    public List<AllowedDomain> getDomains() {
        return repository.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteDomain(@PathVariable Long id) {
        repository.deleteById(id);
    }
}