package az.company.payments.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessage {
    PAYMENT_NOT_FOUND("Payment not found with order id: %s"),
    SERVER_ERROR("Unexpected error occurred");

    private final String message;
}
