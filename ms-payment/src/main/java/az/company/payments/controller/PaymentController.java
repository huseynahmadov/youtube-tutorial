package az.company.payments.controller;

import az.company.payments.model.request.CreatePaymentRequest;
import az.company.payments.model.response.PaymentResponse;
import az.company.payments.service.abstraction.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    @ResponseStatus(CREATED)
    public PaymentResponse pay(@RequestBody CreatePaymentRequest createPaymentRequest) {
        return paymentService.pay(createPaymentRequest);
    }

    @GetMapping("/order/{orderId}")
    public PaymentResponse getPaymentByOrderId(@PathVariable Long orderId) {
        return paymentService.getPaymentByOrderId(orderId);
    }
}
