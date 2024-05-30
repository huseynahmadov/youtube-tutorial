package az.company.payments.model.request;

import az.company.payments.model.enums.PaymentType;
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
