package az.company.payments.model.response;

import az.company.payments.model.enums.PaymentStatus;
import az.company.payments.model.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {
    private Long id;
    private PaymentStatus status;
    private LocalDateTime createdAt;
    private PaymentType paymentType;
}
