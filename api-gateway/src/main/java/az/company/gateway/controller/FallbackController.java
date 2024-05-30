package az.company.gateway.controller;

import az.company.gateway.model.response.FallbackResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/productServiceFallBack")
    public FallbackResponse productServiceFallback() {
        return new FallbackResponse("Product service is down!");
    }

    @GetMapping("/orderServiceFallBack")
    public FallbackResponse orderServiceFallback() {
        return new FallbackResponse("Order service is down!");
    }

    @GetMapping("/paymentServiceFallBack")
    public FallbackResponse paymentServiceFallback() {
        return new FallbackResponse("Payment service is down!");
    }

}
