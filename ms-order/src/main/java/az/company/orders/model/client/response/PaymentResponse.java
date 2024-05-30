package az.company.orders.model.client.response;

import az.company.orders.model.enums.PaymentStatus;
import az.company.orders.model.enums.PaymentType;
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
