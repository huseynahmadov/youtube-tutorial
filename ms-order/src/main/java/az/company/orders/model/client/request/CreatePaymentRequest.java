package az.company.orders.model.client.request;

import az.company.orders.model.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePaymentRequest {
    private Long orderId;
    private PaymentType paymentType;
    private BigDecimal amount;
    private String referenceNumber;
}
