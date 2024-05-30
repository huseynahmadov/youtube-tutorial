package az.company.orders.exception;

import lombok.Builder;

@Builder
public record ErrorResponse(String message) {
}
