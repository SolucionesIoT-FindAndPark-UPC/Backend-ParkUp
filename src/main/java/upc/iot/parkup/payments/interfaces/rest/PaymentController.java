package upc.iot.parkup.payments.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import upc.iot.parkup.payments.domain.model.queries.GetAllPaymentsByUserIdQuery;
import upc.iot.parkup.payments.interfaces.rest.resources.CreatePaymentResource;
import upc.iot.parkup.payments.interfaces.rest.resources.PaymentResource;
import upc.iot.parkup.payments.interfaces.rest.transform.CreatePaymentFromResourceAssembler;
import upc.iot.parkup.payments.interfaces.rest.transform.PaymentResourceFromEntityAssembler;
import upc.iot.parkup.payments.domain.services.PaymentCommandService;
import upc.iot.parkup.payments.domain.services.PaymentQueryService;

import java.util.List;

@Tag(name = "Payment", description = "Payment Management Endpoints")
@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    @Autowired
    private PaymentCommandService commandService;

    @Autowired
    private PaymentQueryService queryService;

    @PostMapping
    public ResponseEntity<PaymentResource> createPayment(@RequestBody CreatePaymentResource resource) {
        var command = CreatePaymentFromResourceAssembler.toCommandFromResource(resource);
        var payment = commandService.handle(command);
        var paymentResource = PaymentResourceFromEntityAssembler.toResourceFromEntity(payment);
        return ResponseEntity.ok(paymentResource);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PaymentResource>> getPaymentsByUserId(@PathVariable Long userId) {
        var query = new GetAllPaymentsByUserIdQuery(userId);
        var payments = queryService.handle(query);
        var resources = payments.stream()
                .map(PaymentResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}
