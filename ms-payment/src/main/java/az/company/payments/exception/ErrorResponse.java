package az.company.payments.exception;

import lombok.Builder;

@Builder
public record ErrorResponse(String message) {
}
