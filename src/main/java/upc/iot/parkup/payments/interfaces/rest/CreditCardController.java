package upc.iot.parkup.payments.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import upc.iot.parkup.payments.domain.model.entities.CreditCard;
import upc.iot.parkup.payments.domain.model.queries.GetAllCreditCardsByUserIdQuery;
import upc.iot.parkup.payments.domain.model.queries.GetCreditCardByIdQuery;
import upc.iot.parkup.payments.interfaces.rest.resources.CreateCreditCardResource;
import upc.iot.parkup.payments.interfaces.rest.resources.DeleteCreditCardResource;
import upc.iot.parkup.payments.interfaces.rest.resources.CreditCardResource;
import upc.iot.parkup.payments.interfaces.rest.transform.CreateCreditCardFromResourceAssembler;
import upc.iot.parkup.payments.interfaces.rest.transform.DeleteCreditCardFromResourceAssembler;
import upc.iot.parkup.payments.interfaces.rest.transform.CreditCardResourceFromEntityAssembler;
import upc.iot.parkup.payments.domain.services.CreditCardCommandService;
import upc.iot.parkup.payments.domain.services.CreditCardQueryService;

import java.util.List;

@Tag(name = "Credit Cards", description = "Credit Card Management Endpoints")
@RestController
@RequestMapping("/api/v1/credit-cards")
public class CreditCardController {

    @Autowired
    private CreditCardCommandService commandService;

    @Autowired
    private CreditCardQueryService queryService;

    @PostMapping
    public ResponseEntity<CreditCardResource> createCreditCard(@RequestBody CreateCreditCardResource resource) {
        var command = CreateCreditCardFromResourceAssembler.toCommandFromResource(resource);
        CreditCard creditCard = commandService.handle(command);
        var creditCardResource = CreditCardResourceFromEntityAssembler.toResourceFromEntity(creditCard);
        return ResponseEntity.ok(creditCardResource);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCreditCard(@RequestBody DeleteCreditCardResource resource) {
        var command = DeleteCreditCardFromResourceAssembler.toCommandFromResource(resource);
        commandService.handle(command);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{creditCardId}")
    public ResponseEntity<CreditCardResource> getCreditCardById(@PathVariable Long creditCardId) {
        var query = new GetCreditCardByIdQuery(creditCardId);
        var creditCard = queryService.handle(query);
        var resource = CreditCardResourceFromEntityAssembler.toResourceFromEntity(creditCard);
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CreditCardResource>> getAllCreditCardsByUserId(@PathVariable Long userId) {
        var query = new GetAllCreditCardsByUserIdQuery(userId);
        var cards = queryService.handle(query);
        var resources = cards.stream()
                .map(CreditCardResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}

