package az.company.payments.service.abstraction;

import az.company.payments.model.request.CreatePaymentRequest;
import az.company.payments.model.response.PaymentResponse;

public interface PaymentService {
    PaymentResponse pay(CreatePaymentRequest createPaymentRequest);

    PaymentResponse getPaymentByOrderId(Long orderId);
}
