package az.company.orders.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessage {

    ORDER_NOT_FOUND("Order not found with id: %s"),
    CLIENT_ERROR("Client error occurred while making the request"),
    SERVER_ERROR("Unexpected error occurred");

    private final String message;
}
