package az.company.orders.exception;

import lombok.Data;
import lombok.Getter;

@Getter
public class CustomFeignException extends RuntimeException {
    private final Integer statusCode;

    public CustomFeignException(Integer statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }
}
